package com.enginecore.bigcam.mng.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 负责设置view。如果已经设置了viewName，那么不做任何操作<br/>
 * 如果数据中包含key : success，那么设置为 defaultView(单key也要输出key)<br/>
 * 如果数据中不包含key : success，那么设置为 jsonView(单key不输出key)
 * Created by yyam on 14-11-9.
 */
public class ViewNameInterceptor extends HandlerInterceptorAdapter {
    private String defaultView;
    private Set<String> forceViewSet;

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public void setForceViewSet(Set<String> forceViewSet) {
        this.forceViewSet = forceViewSet;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String viewName = modelAndView.getViewName();
            if (StringUtils.isBlank(viewName)) {
                modelAndView.setViewName(defaultView);
            } else {
                if (!forceViewSet.contains(viewName)) {
                    if (modelAndView.getModel().containsKey("success")) {
                        modelAndView.setViewName(defaultView);
                    } else {
                        modelAndView.setViewName("jsonView");
                    }
                }
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
