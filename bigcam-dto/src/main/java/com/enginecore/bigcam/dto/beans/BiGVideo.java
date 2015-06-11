package com.enginecore.bigcam.dto.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by yyam on 15-3-20.
 */
public class BiGVideo {
    /**
     * 视频已上传到七牛空间
     */
    public static final String VIDEO_STATUS_UPLOAD = "upload";
    /**
     * 七牛正在处理中
     */
    public static final String VIDEO_STATUS_PERSIST = "persist";
    /**
     * 七牛处理成功完成
     */
    public static final String VIDEO_STATUS_COMPLETE = "complete";
    /**
     * 七牛处理失败
     */
    public static final String VIDEO_STATUS_FAILED = "persist_failed";
    private Integer id;
    private String uuid;
    private Integer userId;
    private String videoCover;
    private String videoContent;
    private Long bitRate;
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

    private Integer width;//分辨率 - 宽
    private Integer height;//分辨率 - 高
    private Long fileSize;//文件大小，单位bytes
    private String codecName;//编码类型
    private String codecType;//文件类型 video/audio
    private String displayAspectRatio;//显示宽高比 e.g. "16:9"

    private String status;//视频状态 (a upload:已上传) b persist:七牛处理中 c complete:可以在app显示 -- a 已上传 暂时废弃
    private String msg;//七牛处理的消息

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getCodecName() {
        return codecName;
    }

    public void setCodecName(String codecName) {
        this.codecName = codecName;
    }

    public String getCodecType() {
        return codecType;
    }

    public void setCodecType(String codecType) {
        this.codecType = codecType;
    }

    public String getDisplayAspectRatio() {
        return displayAspectRatio;
    }

    public void setDisplayAspectRatio(String displayAspectRatio) {
        this.displayAspectRatio = displayAspectRatio;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }

    public Long getBitRate() {
        return bitRate;
    }

    public void setBitRate(Long bitRate) {
        this.bitRate = bitRate;
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
