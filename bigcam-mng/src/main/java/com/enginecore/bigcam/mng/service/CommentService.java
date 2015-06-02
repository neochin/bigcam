package com.enginecore.bigcam.mng.service;

import com.enginecore.bigcam.dto.beans.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 14-11-19.
 */
public interface CommentService {
    List<Comment> refresh(Integer userId, Integer videoId, Integer limit);

    List<Comment> load(Integer userId, Integer videoId, Integer commentId, Integer limit);

    void praise(Integer userId, Integer commentId);

    void unPraise(Integer userId, Integer commentId);
}
