package com.enginecore.bigcam.mng.web;

import com.enginecore.bigcam.dto.beans.Channel;
import com.enginecore.bigcam.mng.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by yyam on 15-4-14.
 */
@Controller
@RequestMapping("channel")
public class ChannelCtl {
    @Autowired
    private ChannelService channelService;

    @RequestMapping("list")
    public List<Channel> list() {
        return channelService.getAllChannels();
    }
}
