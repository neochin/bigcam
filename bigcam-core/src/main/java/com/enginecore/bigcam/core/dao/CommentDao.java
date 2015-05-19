package com.enginecore.bigcam.core.dao;

import com.enginecore.bigcam.dto.beans.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 14-11-19.
 */
@Repository
public interface CommentDao {
    void comment(Comment comment);

    List<Map<String, Object>> refresh(@Param("videoId") Integer videoId, @Param("limit") Integer limit);
    List<Map<String, Object>> load(@Param("videoId") Integer videoId, @Param("commentId") Integer commentId, @Param("limit") Integer limit);

    void praise(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    void unPraise(@Param("commentId") Integer commentId, @Param("userId") Integer userId);
}
