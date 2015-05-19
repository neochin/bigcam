package com.enginecore.bigcam.mng.service;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 14-11-19.
 */
public interface CommentService {
    List<Map<String, Object>> refresh(Integer userId, Integer videoId, Integer limit);

    List<Map<String, Object>> load(Integer userId, Integer videoId, Integer commentId, Integer limit);

    void praise(Integer userId, Integer commentId);

    void unPraise(Integer userId, Integer commentId);
}
