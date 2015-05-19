package com.enginecore.bigcam.mng.service;

import com.enginecore.bigcam.dto.beans.BiGVideo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-13.
 */
public interface VideoService {
    Integer upload(MultipartFile videoCover, String videoDesc, String url,
                   String title, Integer duration, Integer channel) throws Exception;

    List<BiGVideo> list(Integer userId, Integer channel, Integer start, Integer limit);

    List<BiGVideo> liked(Integer userId, Integer start, Integer limit);

    void playOnce(Integer userId, Integer videoId);

    void like(Integer userId, Integer videoId);

    void unLike(Integer userId, Integer videoId);

    void comment(Integer userId, Integer videoId, Integer videoUserId, String commentaryText, Integer repliedUserId);

    List<Map<String, Object>> likedUsers(Integer videoId, Integer start, Integer limit);
}
