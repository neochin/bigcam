package com.enginecore.bigcam.dto.beans;

import java.util.Date;

/**
 * Created by yyam on 15-4-17.
 */
public class UserSuggestion {
    private Integer userId;
    private String email;
    private String suggestion;
    private Date suggestTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getSuggestTime() {
        return suggestTime;
    }

    public void setSuggestTime(Date suggestTime) {
        this.suggestTime = suggestTime;
    }
}
