package com.enginecore.bigcam.dto.beans;

import java.util.Date;

/**
 * Created by yyam on 15-4-15.
 */
public class UserPraiseComment {
    private Integer userId;
    private Integer commentId;
    private Date commentTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
