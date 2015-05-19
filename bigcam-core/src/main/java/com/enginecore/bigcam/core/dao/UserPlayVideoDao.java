package com.enginecore.bigcam.core.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yyam on 15-4-15.
 */
@Repository
public interface UserPlayVideoDao {
    void play(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    Integer played(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
}
