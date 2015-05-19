package com.enginecore.bigcam.mng.web;

import com.enginecore.bigcam.core.SessionManager;
import com.enginecore.bigcam.mng.service.CommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yyam on 14-11-19.
 */
@Controller
@RequestMapping("/comment")
public class CommentCtl {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private CommentService commentService;

    @RequestMapping("/{videoId}")
    public ModelAndView refresh(@PathVariable Integer videoId) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            mv.addObject("data", commentService.refresh(userId, videoId, 20));
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("刷新视频评论列表出现异常，用户：[" + userId + "]，视频：[" + videoId + "]", e);
        }
        return mv;
    }
    @RequestMapping("/load/{videoId}")
    public ModelAndView load(@PathVariable Integer videoId, @RequestParam Integer commentaryId) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            mv.addObject("data", commentService.load(userId, videoId, commentaryId, 20));
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("加载视频评论列表出现异常，用户：[" + userId + "]，视频：[" + videoId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/{commentId}/praise")
    public ModelAndView praise(@PathVariable Integer commentId) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            commentService.praise(userId, commentId);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("赞评论异常。用户[" + userId + "]评论[" + commentId + "]", e);
        }
        return modelAndView;
    }

    @RequestMapping("/{commentId}/unPraise")
    public ModelAndView unPraise(@PathVariable Integer commentId) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        try {
            commentService.unPraise(userId, commentId);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            modelAndView.addObject("success", Boolean.FALSE);
            logger.warn("取消赞评论异常。用户[" + userId + "]评论[" + commentId + "]", e);
        }
        return modelAndView;
    }
}
