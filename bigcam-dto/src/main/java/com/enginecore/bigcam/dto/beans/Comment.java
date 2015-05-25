package com.enginecore.bigcam.dto.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by tony.yang on 2014/10/23.
 */
public class Comment {
    private Integer commentId;
    private String commentText;
    private Integer userId;//评论者。一般都是当前登录用户
    private Integer videoId;//被评论的视频
    private Integer videoUserId;//被评论的视频的上传者
    private Integer repliedUserId;//可能是回复某人的评论。被回复的用户的ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    private Integer praiseTimes;//评论被赞的次数

    private String nickname;//评论者的昵称
    private String profilePhoto;

    private String repliedNickname;//被回复的用户的昵称
    private String repliedProfilePhoto;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getVideoUserId() {
        return videoUserId;
    }

    public void setVideoUserId(Integer videoUserId) {
        this.videoUserId = videoUserId;
    }

    public Integer getRepliedUserId() {
        return repliedUserId;
    }

    public void setRepliedUserId(Integer repliedUserId) {
        this.repliedUserId = repliedUserId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getPraiseTimes() {
        return praiseTimes;
    }

    public void setPraiseTimes(Integer praiseTimes) {
        this.praiseTimes = praiseTimes;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getRepliedNickname() {
        return repliedNickname;
    }

    public void setRepliedNickname(String repliedNickname) {
        this.repliedNickname = repliedNickname;
    }

    public String getRepliedProfilePhoto() {
        return repliedProfilePhoto;
    }

    public void setRepliedProfilePhoto(String repliedProfilePhoto) {
        this.repliedProfilePhoto = repliedProfilePhoto;
    }
}
