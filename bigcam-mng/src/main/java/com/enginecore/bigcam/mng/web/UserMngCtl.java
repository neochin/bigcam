package com.enginecore.bigcam.mng.web;

import com.enginecore.bigcam.core.SessionManager;
import com.enginecore.bigcam.dto.beans.User;
import com.enginecore.bigcam.mng.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by tony.yang on 2014/10/28.
 */
@Controller
@RequestMapping("/user")
public class UserMngCtl {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("accessToken") String accessToken) {
        ModelAndView mv = new ModelAndView();
        try {
            User user = userService.login(username, accessToken);
            HttpSession session = request.getSession(Boolean.TRUE);
            session.setAttribute(SessionManager.CURRENT_USER_KEY, user);
            mv.addObject("success", Boolean.TRUE);
            mv.addObject("nickname", user.getNickname());
        } catch (Exception e) {
            logger.warn("登录失败", e);
            mv.addObject("success", Boolean.FALSE);
            mv.addObject("msg", e.getMessage());
        }
        return mv;
    }

    @RequestMapping("/upload_login")
    public ModelAndView uploadLogin(HttpServletRequest request, @RequestParam("username") String username, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.uploadLogin(username);
            HttpSession session = request.getSession(Boolean.TRUE);
            session.setAttribute(SessionManager.CURRENT_USER_KEY, user);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            logger.warn("登录失败", e);
            modelAndView.addObject("success", Boolean.FALSE);
            String msg = StringUtils.isBlank(e.getMessage()) ? "登录失败" : e.getMessage();
            modelAndView.addObject("msg", msg);
        }
        return modelAndView;
    }

    /**
     * Just check if can get the same session after login request
     * @return
     */
    @RequestMapping("/check")
    public ModelAndView checkLogin() {
        HttpSession session = SessionManager.getCurrentSession();
        ModelAndView mv = new ModelAndView();
        if (session != null && session.getAttribute(SessionManager.CURRENT_USER_KEY) != null) {
            mv.addObject("success", Boolean.TRUE);
            mv.addObject("msg", "login success");
        } else {
            mv.addObject("success", Boolean.FALSE);
        }
        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register(HttpServletRequest request,
            @RequestParam(required = true, value = "username") String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String accessToken,
            @RequestParam(required = false) String openId,
            @RequestParam(required = false) String authType
    ) {
        ModelAndView mv = new ModelAndView();
        try {
            User user = userService.register(username, password, nickname, email, accessToken, openId, authType);
            HttpSession session = request.getSession(Boolean.TRUE);
            session.setAttribute(SessionManager.CURRENT_USER_KEY, user);
            mv.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            mv.addObject("msg", e.getMessage());
            logger.warn("用户注册异常", e);
        }
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userProfile() {
        Integer userId = SessionManager.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        try {
            Map<String, Object> map = userService.userProfile(userId);
            mv.addAllObjects(map);
        } catch (Exception e) {
            mv.addObject("success", false);
            mv.addObject("msg", "获取用户资料失败");
        }
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{userId}")
    public ModelAndView viewUserProfile(@PathVariable(value = "userId") Integer userId) {
        Integer currUserId = SessionManager.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        try {
            Map<String, Object> map = userService.viewUserProfile(userId, currUserId);
            mv.addAllObjects(map);
        } catch (Exception e) {
            mv.addObject("success", false);
            mv.addObject("msg", "获取用户资料失败");
        }
        return mv;
    }

    @RequestMapping("/photoToken")
    public ModelAndView photoToken() {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            mv.addObject("success", userService.uploadToken());
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("获取头像uploadToken异常，用户：[" + userId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/profilePhoto")
    public ModelAndView profilePhoto(@RequestParam("profilePhoto") String profilePhoto) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            userService.uploadProfilePhoto(profilePhoto, userId);
            mv.addObject("success", Boolean.TRUE);
        } catch (IOException e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("上传头像异常，用户：[" + userId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/nickname")
    public ModelAndView updateNickname(@RequestParam String nickname) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            userService.updateNickname(nickname, userId);
            mv.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("设置昵称异常，用户：[" + userId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/gender")
    public ModelAndView updateGender(@RequestParam String gender) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            userService.updateGender(gender, userId);
            mv.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("设置性别异常，用户：[" + userId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/location")
    public ModelAndView updateLocation(@RequestParam Integer provinceId, @RequestParam Integer cityId) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            userService.updateLocation(provinceId, cityId, userId);
            mv.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("设置所在地异常，用户：[" + userId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/signature")
    public ModelAndView updateSignature(@RequestParam String signatureText) {
        ModelAndView mv = new ModelAndView();
        Integer userId = SessionManager.getCurrentUserId();
        try {
            userService.updateSignature(signatureText, userId);
            mv.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("更新简介异常，用户：[" + userId + "]", e);
        }
        return mv;
    }

    @RequestMapping("/like")
    public ModelAndView likeVideo(@RequestParam(required = false) Integer userId) {
        ModelAndView mv = new ModelAndView();
        if (userId == null) {
            userId = SessionManager.getCurrentUserId();
        }
        try {
            mv.addAllObjects(userService.likeVideo(userId));
        } catch (Exception e) {
            mv.addObject("success", Boolean.FALSE);
            logger.warn("获取喜欢的视频异常，用户：[" + userId + "]", e);
        }
        return mv;
    }
}
