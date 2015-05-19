package com.enginecore.bigcam.mng.web;

import com.enginecore.bigcam.core.SessionManager;
import com.enginecore.bigcam.dto.beans.BiGVideo;
import com.enginecore.bigcam.mng.service.VideoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("upload")
    public ModelAndView upload(@RequestParam(value = "videoCover", required = false) MultipartFile videoCover,
           @RequestParam(value = "videoDesc", required = false) String videoDesc,
           @RequestParam(required = false) String url, @RequestParam(required = false) String title,
           @RequestParam(required = false) Integer duration, @RequestParam(required = false) Integer channel) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer videoId = videoService.upload(videoCover, videoDesc, url, title, duration, channel);
            modelAndView.addObject("success", Boolean.TRUE);
            modelAndView.addObject("videoId", videoId);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("上传拍摄视频出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("list")
    public ModelAndView list(@RequestParam(required = false) Integer channel, @RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "30") Integer limit) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            List<BiGVideo> videoList = videoService.list(userId, channel, start, limit);
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
                                @RequestParam Integer videoUserId, @RequestParam(required = false) Integer repliedUserId) {
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
    public ModelAndView likedUsers(@PathVariable Integer videoId, @RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "10") Integer limit) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Map<String, Object>> likedUsers = videoService.likedUsers(videoId, start, limit);
            modelAndView.addObject("data", likedUsers);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("获取喜欢的用户出现异常", e);
        }
        return modelAndView;
    }

    @RequestMapping("likedVideos")
    public ModelAndView likedVideos(@RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "30") Integer limit) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            List<BiGVideo> biGVideos = videoService.liked(userId, start, limit);
            modelAndView.addObject("data", biGVideos);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("获取喜欢的用户出现异常", e);
        }
        return modelAndView;
    }
}
