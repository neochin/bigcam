package com.enginecore.bigcam.mng.web;

import com.enginecore.bigcam.core.SessionManager;
import com.enginecore.bigcam.core.util.IGtPushUtil;
import com.enginecore.bigcam.dto.beans.BiGVideo;
import com.enginecore.bigcam.mng.service.VideoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-3.
 */
@Controller
@RequestMapping("video")
public class VideoCtl {
    private Log logger = LogFactory.getLog(VideoCtl.class);
    @Autowired
    private VideoService videoService;

    @RequestMapping("uploadToken")
    public ModelAndView getUploadToken() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("token", videoService.uploadToken());
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("获取上传令牌出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("delete/{videoId}")
    public ModelAndView delete(@PathVariable Integer videoId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            videoService.delete(videoId);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("删除视频出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("persistNotify")
    public ModelAndView persistNotify(@RequestBody String persistResult) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            videoService.persistResult(persistResult);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("处理七牛处理完成通知出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("upload")
    public ModelAndView upload(@RequestParam(value = "videoDesc", required = false) String videoDesc,
           @RequestParam(required = false) String videoContent, @RequestParam(required = false) String title,
           @RequestParam(required = false) Double duration, @RequestParam(required = false) Integer channel,
           @RequestParam(required = false) Integer width, @RequestParam(required = false) Integer height,
           @RequestParam(required = false) Long bitRate, @RequestParam(required = false) Long fileSize,
           @RequestParam(required = false) String codecName, @RequestParam(required = false) String codecType,
           @RequestParam(required = false) String displayAspectRatio, @RequestParam(required = false, defaultValue = "15") Long frameOffset) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer videoId = videoService.upload(videoDesc, videoContent, title, duration.intValue(),
                    channel, bitRate, width, height, fileSize, codecName, codecType, displayAspectRatio, frameOffset);
            modelAndView.addObject("success", Boolean.TRUE);
            modelAndView.addObject("videoId", videoId);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("上传拍摄视频出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("list")
    public ModelAndView list(@RequestParam(required = false) String keyword,
             @RequestParam(required = false) Integer channel, @RequestParam(required = false) String videoStatus,
             @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            List<BiGVideo> videoList = videoService.list(keyword, userId, channel, videoStatus, pageNum, pageSize);
            modelAndView.addObject("data", videoList);
        } catch (Exception e) {
            logger.warn("获取视频列表异常", e);
            modelAndView.addObject("success", Boolean.FALSE);
        }
        return modelAndView;
    }

    @RequestMapping("{videoId}/play")
    public ModelAndView play(@PathVariable Integer videoId) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            videoService.playOnce(userId, videoId);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            logger.warn("增加视频播放次数异常。视频id：[" + videoId + "]", e);
            modelAndView.addObject("success", Boolean.FALSE);
        }
        return modelAndView;
    }

    @RequestMapping("{videoId}/like")
    public ModelAndView like(@PathVariable Integer videoId) {
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        ModelAndView modelAndView = new ModelAndView();
        try {
            videoService.like(userId, videoId);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            logger.warn("喜欢视频出现异常。用户[" + userId + "]视频[" + videoId + "]", e);
            modelAndView.addObject("success", Boolean.FALSE);
        }
        return modelAndView;
    }

    @RequestMapping("{videoId}/unLike")
    public ModelAndView unLike(@PathVariable Integer videoId) {
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        ModelAndView modelAndView = new ModelAndView();
        try {
            videoService.unLike(userId, videoId);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            logger.warn("喜欢视频出现异常。用户[" + userId + "]视频[" + videoId + "]", e);
            modelAndView.addObject("success", Boolean.FALSE);
        }
        return modelAndView;
    }

    @RequestMapping("/comment")
    public ModelAndView comment(@RequestParam String commentaryText, @RequestParam Integer videoId,
                                @RequestParam(required = false) Integer videoUserId, @RequestParam(required = false) Integer repliedUserId) {
        ModelAndView mv = new ModelAndView();
        Integer userId = null;
        try {
            userId = SessionManager.getCurrentUserId();
        } catch (IllegalArgumentException e) {
            mv.addObject("success", Boolean.FALSE);
            mv.addObject("msg", "登录后才能评论");
            logger.warn("评论出现异常", e);
            return mv;
        }
        try {
            videoService.comment(userId, videoId, videoUserId, commentaryText, repliedUserId);
            mv.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("评论视频出现异常，用户：[" + userId + "]，评论视频：[" + videoId + "]，评论内容：[" + commentaryText + "]", e);
        }
        return mv;
    }

    @RequestMapping("{videoId}/likedUsers")
    public ModelAndView likedUsers(@PathVariable Integer videoId, @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Map<String, Object>> likedUsers = videoService.likedUsers(videoId, pageNum, pageSize);
            modelAndView.addObject("data", likedUsers);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("获取喜欢的用户出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("likedVideos")
    public ModelAndView likedVideos(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            List<BiGVideo> biGVideos = videoService.liked(userId, pageNum, pageSize);
            modelAndView.addObject("data", biGVideos);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("获取喜欢的用户出现异常", e);
        }
        return modelAndView;
    }
}
