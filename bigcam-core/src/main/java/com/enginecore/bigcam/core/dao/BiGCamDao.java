package com.enginecore.bigcam.core.dao;

import com.enginecore.bigcam.dto.beans.BiGVideo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yyam on 15-4-8.
 */
@Repository
public interface BiGCamDao {
    void save(BiGVideo biGVideo);

    List<BiGVideo> list(@Param("keyword") String keyword, @Param("channel") Integer channel, @Param("start") Integer start, @Param("limit") Integer limit);

    List<BiGVideo> liked(@Param("userId") Integer userId, @Param("start") Integer start, @Param("limit") Integer limit);

    void addPlayTimes(Integer videoId);

    void like(Integer videoId);

    void unLike(Integer videoId);

    void comment(Integer videoId);

    void persistResult(@Param("sourceKey") String sourceKey, @Param("persistKey") String persistKey,
           @Param("videoCover") String videoCover,
           @Param("videoStatus") String videoStatus, @Param("videoMsg") String videoMsg);
}
