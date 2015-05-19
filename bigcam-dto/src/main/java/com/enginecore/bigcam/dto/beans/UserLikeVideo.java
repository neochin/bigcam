package com.enginecore.bigcam.dto.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by yyam on 15-4-15.
 */
public class UserLikeVideo {
    private Integer userId;
    private Integer videoId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date likeTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}
