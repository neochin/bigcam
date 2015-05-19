package com.enginecore.bigcam.dto.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by yyam on 15-3-20.
 */
public class BiGVideo {
    private Integer id;
    private String uuid;
    private Integer userId;
    private String cover;
    private String url;
    private String title;
    private String desc;
    private Integer duration;//时长，单位s
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    private Integer commentTimes = 0;
    private Integer likeTimes = 0;
    private Integer playTimes = 0;
    private Integer shareTimes = 0;
    private Integer storeTimes = 0;

    private Integer channel;
    private String channelText;

    private Boolean liked;

    public Boolean isLiked() {
        return liked;
    }

    public void setLiked(Integer count) {
        if (count == null) {
            this.liked = false;
        }
        if (count < 1) {
            this.liked = false;
        }
        if (count == 1) {
            this.liked = true;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(Integer commentTimes) {
        this.commentTimes = commentTimes;
    }

    public Integer getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(Integer shareTimes) {
        this.shareTimes = shareTimes;
    }

    public Integer getStoreTimes() {
        return storeTimes;
    }

    public void setStoreTimes(Integer storeTimes) {
        this.storeTimes = storeTimes;
    }

    public Integer getLikeTimes() {
        return likeTimes;
    }

    public void setLikeTimes(Integer likeTimes) {
        this.likeTimes = likeTimes;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getChannelText() {
        return channelText;
    }

    public void setChannelText(String channelText) {
        this.channelText = channelText;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(Integer playTimes) {
        this.playTimes = playTimes;
    }
}
