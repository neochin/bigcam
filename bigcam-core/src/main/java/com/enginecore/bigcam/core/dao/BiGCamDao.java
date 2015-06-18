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

    List<BiGVideo> list(@Param("keyword") String keyword, @Param("videoStatus") String videoStatus, @Param("channel") Integer channel);

    List<BiGVideo> liked(@Param("userId") Integer userId);

    void addPlayTimes(Integer videoId);

    void like(Integer videoId);

    void unLike(Integer videoId);

    void delete(Integer videoId);

    void comment(Integer videoId);

    void persistResult(@Param("sourceKey") String sourceKey, @Param("persistKey") String persistKey,
           @Param("videoCover") String videoCover,
           @Param("videoStatus") String videoStatus, @Param("videoMsg") String videoMsg);
}
