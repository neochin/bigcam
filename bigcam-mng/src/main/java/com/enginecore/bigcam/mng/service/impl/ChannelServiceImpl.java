package com.enginecore.bigcam.mng.service.impl;

import com.enginecore.bigcam.core.dao.ChannelDao;
import com.enginecore.bigcam.dto.beans.Channel;
import com.enginecore.bigcam.mng.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yyam on 15-4-14.
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelDao channelDao;

    @Override
    public List<Channel> getAllChannels() {
        return channelDao.list();
    }
}
