package com.enginecore.bigcam.mng.service;

import com.enginecore.bigcam.dto.beans.BiGVideo;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 15-4-13.
 */
public interface VideoService {
    String uploadToken();

    Integer upload(String videoDesc, String videoContent, String title, Integer duration, Integer channel,
                   Long bitRate, Integer width, Integer height, Long fileSize, String codecName, String codecType, String displayAspectRatio, Long frameOffset) throws Exception;

    void delete(Integer videoId);

    void persistResult(String persistResult);

    List<BiGVideo> list(String keyword, Integer userId, Integer channel, String videoStatus, Integer pageNum, Integer pageSize);

    List<BiGVideo> liked(Integer userId, Integer pageNum, Integer pageSize);

    void playOnce(Integer userId, Integer videoId);

    void like(Integer userId, Integer videoId);

    void unLike(Integer userId, Integer videoId);

    void comment(Integer userId, Integer videoId, Integer videoUserId, String commentaryText, Integer repliedUserId);

    List<Map<String, Object>> likedUsers(Integer videoId, Integer pageNum, Integer pageSize);
}
