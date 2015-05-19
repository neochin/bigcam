package com.enginecore.bigcam.mng.service.impl;

import com.enginecore.bigcam.core.dao.BiGCamDao;
import com.enginecore.bigcam.core.dao.CommentDao;
import com.enginecore.bigcam.core.dao.UserLikeVideoDao;
import com.enginecore.bigcam.core.dao.UserPlayVideoDao;
import com.enginecore.bigcam.core.util.UUIDGenerator;
import com.enginecore.bigcam.dto.beans.BiGVideo;
import com.enginecore.bigcam.dto.beans.Comment;
import com.enginecore.bigcam.mng.service.GridFSService;
import com.enginecore.bigcam.mng.service.VideoService;
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
    @Autowired
    private GridFSService gridFSService;

    @Value("${mongo.video_cover}")
    private String videoCoverBucket;

    @Override
    public Integer upload(MultipartFile videoCover, String videoDesc, String url,
                          String title, Integer duration, Integer channel) throws Exception{
        String uuid = UUIDGenerator.generate();
        String coverFilename = "";
        String fileSuffix = "";
        if (videoCover != null) {
            fileSuffix = StringUtils.substringAfterLast(videoCover.getOriginalFilename(), ".");
            coverFilename = "cover_" + uuid + "." + fileSuffix;
        }
        try {
            if (videoCover != null) {
                gridFSService.uploadFileToGridFS(videoCover.getInputStream(), coverFilename, videoCoverBucket);
            }
            BiGVideo biGVideo = new BiGVideo();
            biGVideo.setUrl(url);
            biGVideo.setChannel(channel);
            biGVideo.setCommentTimes(0);
            biGVideo.setCover(coverFilename);
            biGVideo.setDesc(videoDesc);
            biGVideo.setDuration(duration);
            biGVideo.setLikeTimes(0);
            biGVideo.setPlayTimes(0);
            biGVideo.setTitle(title);
            biGCamDao.save(biGVideo);
            return biGVideo.getId();
        }  catch (Exception e){
            logger.warn("上传视频出现异常", e);
            //任何步骤出现异常，都尝试删除已经上传的文件
            gridFSService.removeFile(coverFilename, videoCoverBucket);
            throw new Exception("上传视频出现异常", e);
        }
    }

    @Override
    public List<BiGVideo> list(Integer userId, Integer channel, Integer start, Integer limit) {
        List<BiGVideo> list = biGCamDao.list(channel, start, limit);
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
