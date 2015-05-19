package com.enginecore.bigcam.core.dao;

import com.enginecore.bigcam.dto.beans.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yyam on 15-4-14.
 */
@Repository
public interface ChannelDao {
    List<Channel> list();
}
