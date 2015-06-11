package com.enginecore.bigcam.mng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enginecore.bigcam.core.dao.BiGCamDao;
import com.enginecore.bigcam.core.dao.CommentDao;
import com.enginecore.bigcam.core.dao.UserLikeVideoDao;
import com.enginecore.bigcam.core.dao.UserPlayVideoDao;
import com.enginecore.bigcam.core.util.UUIDGenerator;
import com.enginecore.bigcam.dto.beans.BiGVideo;
import com.enginecore.bigcam.dto.beans.Comment;
import com.enginecore.bigcam.mng.service.GridFSService;
import com.enginecore.bigcam.mng.service.VideoService;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-13.
 */
@Service
public class VideoServiceImpl implements VideoService{
    private Log logger = LogFactory.getLog(VideoServiceImpl.class);
    @Autowired
    private BiGCamDao biGCamDao;
    @Autowired
    private UserLikeVideoDao userLikeVideoDao;
    @Autowired
    private UserPlayVideoDao userPlayVideoDao;
    @Autowired
    private CommentDao commentDao;

    @Value("${mongo.video_cover}")
    private String videoCoverBucket;
    @Value("${bigcam.qiniu.access_key}")
    private String accessKey;
    @Value("${bigcam.qiniu.secret_key}")
    private String secretKey;
    @Value("${bigcam.qiniu.video_bucket}")
    private String videoBucket;

    @Value("${bigcam.qiniu.max_bit_rate}")
    private Long maxBitRate;
    @Value("${bigcam.qiniu.force_persist}")
    private Boolean forcePersist;
    @Value("${bigcam.qiniu.pipeline}")
    private String pipeline;
    @Value("${bigcam.qiniu.notifyURL}")
    private String notifyURL;
    @Value("${bigcam.qiniu.dest_video_format}")
    private String destVideoFormat;

    @Override
    public String uploadToken() {
        return Auth.create(accessKey, secretKey).uploadToken(videoBucket, null, 3600, new StringMap().put("returnBody", "{\"key\": $(key), \"hash\": $(etag), \"avinfo\": $(avinfo)"));
    }

    @Override
    public Integer upload(String videoDesc, String videoContent, String title, Integer duration, Integer channel,
        Long bitRate, Integer width, Integer height, Long fileSize, String codecName, String codecType, String displayAspectRatio) throws Exception{
        String uuid = UUIDGenerator.generate();

        try {
            BiGVideo biGVideo = new BiGVideo();
            biGVideo.setUuid(uuid);
            biGVideo.setDesc(videoDesc);
            biGVideo.setVideoContent(videoContent);
            biGVideo.setTitle(title);
            biGVideo.setDuration(duration);
            biGVideo.setChannel(channel);
            biGVideo.setBitRate(bitRate);
            biGVideo.setWidth(width);
            biGVideo.setHeight(height);
            biGVideo.setFileSize(fileSize);
            biGVideo.setCodecName(codecName);
            biGVideo.setCodecType(codecType);
            biGVideo.setDisplayAspectRatio(displayAspectRatio);
            biGVideo.setStatus(BiGVideo.VIDEO_STATUS_PERSIST);

            biGVideo.setCommentTimes(0);
            biGVideo.setLikeTimes(0);
            biGVideo.setPlayTimes(0);
            StringBuffer persistOps = new StringBuffer("avthumb/");
            persistOps.append(destVideoFormat);
            if (width > 1280 || height > 720) {
                persistOps.append("/s/1280x720/autoscale/1");
            }
            persistOps.append("/aspect/").append(displayAspectRatio);
            if (bitRate > maxBitRate) {
                persistOps.append("/vb/").append(maxBitRate);
            }
            persistOps.append(";");
            persistOps.append("vframe/png/offset/10");
            OperationManager operationManager = new OperationManager(Auth.create(accessKey, secretKey));
            operationManager.pfop(videoBucket, videoContent, persistOps.toString(),
                    new StringMap().putNotEmpty("notifyURL", notifyURL).putWhen("force", 1, forcePersist).putNotEmpty("pipeline", pipeline));
            biGCamDao.save(biGVideo);
            return biGVideo.getId();
        }  catch (Exception e){
            logger.warn("上传视频出现异常", e);
            //任何步骤出现异常，都尝试删除已经上传的文件
            throw new Exception("上传视频出现异常", e);
        }
    }

    @Override
    public void persistResult(String persistResult) {
        logger.info("处理结果:" + System.getProperty("line.separator") + persistResult);
        JSONObject json = JSON.parseObject(persistResult);
        String source = json.getString("inputKey");
        JSONArray items = json.getJSONArray("items");
        JSONObject item1 = items.getJSONObject(0);
        JSONObject item2 = items.getJSONObject(1);
        String persistStatus = BiGVideo.VIDEO_STATUS_COMPLETE;
        String persistMsg = item1.getString("desc");
        String persistKey = null;
        if (item1.getInteger("code") != 0) {//转码失败
            persistStatus = BiGVideo.VIDEO_STATUS_FAILED;
            persistMsg = item1.getString("error");
        } else {//转码成功
            persistKey = item1.getString("key");
        }
        String cover = null;
        if (item2.getInteger("code") == 0) {//截图成功
            cover = item2.getString("key");
        }
        biGCamDao.persistResult(source, persistKey, cover, persistStatus, persistMsg);
    }

    @Override
    public List<BiGVideo> list(String keyword, Integer userId, Integer channel, Integer start, Integer limit) {
        List<BiGVideo> list = biGCamDao.list(keyword, channel, start, limit);
        addLiked4List(list, userId);
        return list;
    }

    @Override
    public List<BiGVideo> liked(Integer userId, Integer start, Integer limit) {
        List<BiGVideo> list = biGCamDao.liked(userId, start, limit);
        addLiked4List(list, userId);
        return list;
    }

    private void addLiked4List(List<BiGVideo> list, Integer userId) {
        if (userId != null) {
            for (BiGVideo biGVideo : list) {
                biGVideo.setLiked(userLikeVideoDao.liked(userId, biGVideo.getId()));
            }
        }
    }

    @Override
    public void playOnce(Integer userId, Integer videoId) {
        if (userId != null) {
            userPlayVideoDao.play(userId, videoId);
        }
        biGCamDao.addPlayTimes(videoId);
    }

    @Override
    public void like(Integer userId, Integer videoId) {
        if (userId != null){
            userLikeVideoDao.like(userId, videoId);
        }
        biGCamDao.like(videoId);
    }

    @Override
    public void unLike(Integer userId, Integer videoId) {
        if (userId != null){
            userLikeVideoDao.unLike(userId, videoId);
        }
        biGCamDao.unLike(videoId);
    }

    @Override
    public void comment(Integer userId, Integer videoId, Integer videoUserId, String commentaryText, Integer repliedUserId) {
        biGCamDao.comment(videoId);
        Comment commentary = new Comment();
        commentary.setVideoUserId(videoUserId);
        commentary.setUserId(userId);
        commentary.setVideoId(videoId);
        commentary.setCommentText(commentaryText);
        commentary.setRepliedUserId(repliedUserId);
        commentDao.comment(commentary);
        //TODO 推送给视频上传者
        if (repliedUserId != null) {
            //TODO 推送给被回复的人
        }
    }

    @Override
    public List<Map<String, Object>> likedUsers(Integer videoId, Integer start, Integer limit) {
        return userLikeVideoDao.likedUsers(videoId, start, limit);
    }
}
