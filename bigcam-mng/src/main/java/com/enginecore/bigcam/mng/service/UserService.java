package com.enginecore.bigcam.mng.service;

import com.enginecore.bigcam.dto.beans.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yyam on 14-11-7.
 */
public interface UserService {
    User login(String username, String password) throws Exception;

    User uploadLogin(String username) throws Exception;

    User register(String username, String password, String nickname, String email, String accessToken, String openId, String authType) throws Exception;

    /**
     * 获取当前登录用户基本信息
     * @param userId
     * @return
     */
    Map<String, Object> userProfile(Integer userId);

    /**
     * 查看某位用户的基本信息
     * @param userId
     * @param currUserId
     * @return
     */
    Map<String, Object> viewUserProfile(Integer userId, Integer currUserId);

    String uploadToken();

    void uploadProfilePhoto(String profilePhoto, Integer userId) throws IOException;

    void updateNickname(String nickname, Integer userId);

    void updateGender(String gender, Integer userId);

    void updateLocation(Integer provinceId, Integer cityId, Integer userId);

    List<Map<String, String>> province();

    List<Map<String, String>> city(Integer provinceId);

    void updateSignature(String signature, Integer userId);

    Map<String, Object> likeVideo(Integer userId);

}
