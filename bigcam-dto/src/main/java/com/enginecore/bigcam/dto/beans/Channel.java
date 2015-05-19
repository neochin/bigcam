package com.enginecore.bigcam.dto.beans;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by yyam on 15-4-3.
 */
public class Channel {
    @JSONField(name = "value")
    private Integer id;
    @JSONField(name = "text")
    private String channelText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelText() {
        return channelText;
    }

    public void setChannelText(String channelText) {
        this.channelText = channelText;
    }
}
