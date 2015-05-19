package com.enginecore.bigcam.core.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yyam on 15-4-15.
 */
@Repository
public interface UserPraiseCommentDao {
    void praise(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    void unPraise(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    Integer praised(@Param("userId") Integer userId, @Param("commentId") Integer commentId);
}
