package com.enginecore.bigcam.mng.interceptor;

import com.enginecore.bigcam.core.SessionManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Created by yyam on 14-11-6.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private String loginURI;
    private String registerURI;

    public void setLoginURI(String loginURI) {
        this.loginURI = loginURI;
    }

    public void setRegisterURI(String registerURI) {
        this.registerURI = registerURI;
    }

    private Set<String> skipTokenURI;

    public void setSkipTokenURI(Set<String> skipTokenURI) {
        this.skipTokenURI = skipTokenURI;
    }

    private boolean needLogin(HttpServletResponse response, String requestURI) throws IOException {
        if (loginURI.equals(requestURI) || registerURI.equals(requestURI)) {
            return true;
        }
        if (skip(requestURI)) {
            return true;
        }
        response.setStatus(403);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write("{\"success\":false, \"msg\":\"need login\"}");
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession currSession = request.getSession();
        if (currSession == null) {
            return needLogin(response, requestURI);
        } else {
            Object currUser = currSession.getAttribute(SessionManager.CURRENT_USER_KEY);
            SessionManager.setCurrentSession(request.getSession());
            if (currUser == null) {
                return needLogin(response, requestURI);
            }
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 每次请求处理完成后，把线程中的HttpSession对象删除
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        SessionManager.removeCurrentSession();
    }

    private Boolean skip(String requestURI) {
        if (CollectionUtils.isNotEmpty(skipTokenURI)) {
            if (skipTokenURI.contains(requestURI)) {
                return true;
            }
            for (String toSkip : skipTokenURI) {
                UriTemplate template = new UriTemplate(toSkip);
                if (template.matches(requestURI)) {
                    return true;
                }
            }
        }
        return false;
    }
}
