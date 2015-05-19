package com.enginecore.bigcam.mng.web;

import com.enginecore.bigcam.core.SessionManager;
import com.enginecore.bigcam.mng.service.SuggestionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-17.
 */
@Controller
@RequestMapping("suggestion")
public class SuggestionCtl {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("suggest")
    public ModelAndView suggest(@RequestParam(value = "userEmail", required = false) String userEmail, @RequestParam("userSuggestion") String userSuggestion) {
        Integer userId = SessionManager.getCurrentUserIdIgnoreLogin();
        ModelAndView modelAndView = new ModelAndView();
        try {
            suggestionService.suggest(userId, userEmail, userSuggestion);
            modelAndView.addObject("success", Boolean.TRUE);
        } catch (Exception e) {
            logger.warn("保存用户反馈出现异常", e);
            modelAndView.addObject("success", Boolean.FALSE);
        }
        return modelAndView;
    }

    @RequestMapping("list")
    public ModelAndView list(@RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "30") Integer limit) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Map<String, Object>> list = suggestionService.list(start, limit);
            modelAndView.addObject("data", list);
        } catch (Exception e) {
            logger.warn("获取用户反馈列表出现异常", e);
            modelAndView.addObject("success", Boolean.FALSE);
        }
        return modelAndView;
    }
}
