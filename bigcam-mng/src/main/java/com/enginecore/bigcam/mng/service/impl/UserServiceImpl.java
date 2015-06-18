package com.enginecore.bigcam.mng.service.impl;

import com.enginecore.bigcam.core.dao.SignatureDao;
import com.enginecore.bigcam.core.dao.UserDao;
import com.enginecore.bigcam.core.util.EncryptUtil;
import com.enginecore.bigcam.core.util.Gender;
import com.enginecore.bigcam.dto.beans.User;
import com.enginecore.bigcam.mng.service.UserService;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yyam on 14-11-7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SignatureDao signatureDao;
    @Value("${bigcam.qiniu.access_key}")
    private String accessKey;
    @Value("${bigcam.qiniu.secret_key}")
    private String secretKey;
    @Value("${bigcam.qiniu.photo_bucket}")
    private String photoBucket;

    @Override
    public User login(String username, String accessToken) throws Exception {
        //password = EncryptUtil.md5(password);
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new Exception("用户名不存在");
        }
        if (!user.getAccessToken().equals(accessToken)) {
            throw new Exception("授权信息不正确");
        }
        return user;
    }
    @Override
    public User uploadLogin(String username) throws Exception {
        //password = EncryptUtil.md5(password);
        User user = userDao.findByNickname(username);
        if (user == null) {
            user = userDao.findByUsername(username);
        }
        if (user == null) {
            throw new Exception("用户名/昵称 " + username + " 不存在");
        }
        return user;
    }

    @Override
    public User register(String username, String password, String nickname, String email, String accessToken, String openId, String authType) throws Exception{
        User existUser = userDao.findByUsername(username);
        if (existUser != null) {
            throw new Exception("用户名已经存在");
        }
        if (StringUtils.isNotBlank(email)) {
            existUser = userDao.findByEmail(email);
            if (existUser != null) {
                throw new Exception("邮箱已经注册");
            }
        }
        if (StringUtils.isBlank(nickname)) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        nickname = generateNickname(nickname);
        User user = new User();
        user.setNickname(nickname);
        user.setPassword(EncryptUtil.md5(password));
        user.setEmail(email);
        user.setUsername(username);
        user.setAccessToken(accessToken);
        user.setOpenId(openId);
        user.setAuthType(authType);
        userDao.register(user);
        return user;
    }

    private String generateNickname(String nickname) {
        User user = userDao.findByNickname(nickname);
        if (user == null) {
            return nickname;
        }
        nickname += RandomUtils.nextInt(10, 100);
        return generateNickname(nickname);
    }

    @Override
    public Map<String, Object> userProfile(Integer userId) {
        Map<String, Object> map = userDao.userProfile(userId);
        Integer likeTimes = userDao.likeTimes(userId);
        Integer followerNum = userDao.followerNum(userId);
        Integer attentionNum = userDao.attentionNum(userId);
        Integer likeNum = userDao.likeNum(userId);
        Integer topicNum = userDao.topicNum(userId);
        map.put("likeTimes", likeTimes);
        map.put("follower", followerNum);
        map.put("attention", attentionNum);
        map.put("likeNum", likeNum);
        map.put("topic", topicNum);
        return map;
    }

    @Override
    public Map<String, Object> viewUserProfile(Integer userId, Integer currUserId) {
        Map<String, Object> map = userProfile(userId);
        Boolean followTo = userDao.followTo(userId, currUserId) > 0 ? true : false;
        Boolean attentionTo = userDao.followTo(currUserId, userId) > 0 ? true : false;
        map.put("isFollower", followTo);
        map.put("followed", attentionTo);
        return map;
    }

    @Override
    public String uploadToken() {
        return Auth.create(accessKey, secretKey).uploadToken(photoBucket);
    }

    @Override
    public void uploadProfilePhoto(String profilePhoto, Integer userId) throws IOException {
        userDao.setProfilePhoto(profilePhoto, userId);
    }

    @Override
    public void updateNickname(String nickname, Integer userId) {
        userDao.updateNickname(nickname, userId);
    }

    @Override
    public void updateGender(String gender, Integer userId) {
        Gender matchGender = Gender.match(gender);
        userDao.updateGender(matchGender.toString(), userId);
    }

    @Override
    public void updateLocation(Integer provinceId, Integer cityId, Integer userId) {
        userDao.updateLocation(provinceId, cityId, userId);
    }

    @Override
    public void updateSignature(String signatureText, Integer userId) {
        //在 签名表 中，新增一条记录。用于记录历史签名
        signatureDao.updateSignature(signatureText, userId);
        //同时，user中只保存最新的签名
        userDao.setSignature(signatureText, userId);
    }

    @Override
    public Map<String, Object> likeVideo(Integer userId) {
        Map<String, Object> data = userDao.likeVideo(userId, 20);
        return data;
    }
}
