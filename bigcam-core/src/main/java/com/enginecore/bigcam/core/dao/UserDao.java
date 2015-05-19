package com.enginecore.bigcam.core.dao;

import com.enginecore.bigcam.dto.beans.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tony.yang on 2014/10/22.
 */
@Repository
public interface UserDao {

    /**
     * 获取用户的基本信息
     * @param userId
     * @return
     */
    Map<String, Object> userProfile(Integer userId);
    List<Integer> searchRefresh(@Param("word") String word, @Param("limit") Integer limit);
    List<Integer> searchLoad(@Param("word") String word, @Param("userId") Integer userId, @Param("limit") Integer limit);

    User findByEmail(String email);

    User findByUsername(String username);
    User findByNickname(String nickname);

    void register(User user);

    /**
     * 指定用户被喜欢的次数
     * @param userId
     * @return
     */
    Integer likeTimes(Integer userId);

    /**
     * 指定用户喜欢的足迹的个数
     * @param userId
     * @return
     */
    Integer likeNum(Integer userId);

    /**
     * 指定用户的粉丝的数量
     * @param userId
     * @return
     */
    Integer followerNum(Integer userId);

    /**
     * 指定用户的关注的人数
     * @param userId
     * @return
     */
    Integer attentionNum(Integer userId);

    /**
     * 指定用户创建、参与的话题总数
     * @param userId
     * @return
     */
    Integer topicNum(Integer userId);

    /**
     * 查询用户(id=followerId)是否关注了用户(id=userId)
     * @param followerId    粉丝ID
     * @param userId        关注者ID
     * @return  0 用户(id=followerId)没有关注用户(id=userId)
     *          1 用户(id=followerId)关注了用户(id=userId)
     */
    Integer followTo(@Param("followerId") Integer followerId, @Param("userId") Integer userId);

    void updateNickname(@Param("nickname") String nickname, @Param("userId") Integer userId);

    void updateGender(@Param("gender") String gender, @Param("userId") Integer userId);

    void updateLocation(@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId, @Param("userId") Integer userId);

    void setProfilePhoto(@Param("profilePhoto") String profilePhoto, @Param("userId") Integer userId);

    void setSignature(@Param("signatureText") String signatureText, @Param("userId") Integer userId);

    /**
     * 指定用户上传的视频被分享的总次数
     * @param userId
     * @return
     */
    Integer shareTimes(Integer userId);

    /**
     * provinceNum  用户留下足迹的一级地名(国家，省份，地区)个数
     * cityNum  用户留下足迹的二级地名(市，区)个数
     * destinationNum   用户留下足迹的目的地个数
     * @param userId
     * @return
     */
    Map<String, Object> destinationNum(Integer userId);

    /**
     * 获取用户个人成就的基本信息
     * @param userId
     * @return
     */
    Map<String, Object> personalAchievement(Integer userId);

    /**
     * 加载用户最近的 limit 条足迹所在的位置信息
     * @param userId
     * @param limit
     * @return
     */
    Map<String, Object> locations(@Param("userId") Integer userId, @Param("limit") Integer limit);

    Map<String, Object> likeVideo(@Param("userId") Integer userId, @Param("limit") Integer limit);

    /**
     * 把用户的足迹个数+1
     * @param userId
     */
    void addPace(Integer userId);

    void addJourney(@Param("userId") Integer userId, @Param("journey") double journey);
}
