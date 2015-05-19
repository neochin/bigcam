package com.enginecore.bigcam.core;

import com.enginecore.bigcam.dto.beans.User;

import javax.servlet.http.HttpSession;

/**
 * 管理会话
 * Created by yyam on 14-11-6.
 */
public class SessionManager {
    public static final String CURRENT_USER_KEY = "curr_user";
    private static final ThreadLocal<HttpSession> CURRENT_SESSION = new ThreadLocal<HttpSession>();

    public static void setCurrentSession(HttpSession session) {
        CURRENT_SESSION.set(session);
    }

    public static HttpSession getCurrentSession() {
        return CURRENT_SESSION.get();
    }

    public static void removeCurrentSession() {
        CURRENT_SESSION.remove();
    }

    public static User getCurrentUser() {
        if (getCurrentSession() == null) {
            throw new IllegalStateException("此操作需要登录才能进行");
        }
        Object user = getCurrentSession().getAttribute(CURRENT_USER_KEY);
        if (user != null) {
            return (User) user;
        }
        return null;
    }

    /**
     * login : userId<br/>
     * not login : throw IllegalStateException
     * @return
     */
    public static Integer getCurrentUserId() {
        User user = getCurrentUser();
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }

    /**
     * login : userId<br/>
     * not login : null
     * @return
     */
    public static Integer getCurrentUserIdIgnoreLogin() {
        if (getCurrentSession() == null) {
            return null;
        }
        User user = getCurrentUser();
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }
}
