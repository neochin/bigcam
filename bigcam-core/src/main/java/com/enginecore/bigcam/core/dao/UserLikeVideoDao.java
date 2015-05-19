package com.enginecore.bigcam.core.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-15.
 */
@Repository
public interface UserLikeVideoDao {
    void like(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    void unLike(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    Integer liked(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    List<Map<String, Object>> likedUsers(@Param("videoId") Integer videoId, @Param("start") Integer start, @Param("limit") Integer limit);
}
