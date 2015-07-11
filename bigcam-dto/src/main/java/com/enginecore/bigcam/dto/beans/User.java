package com.enginecore.bigcam.dto.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tony.yang on 2014/10/22.
 */
public class User implements Serializable {
    private Integer userId;
    private String email;
    private String nickname;
    private String username;
    private String password;
    private String profilePhoto;//用户头像
    private String phoneNum;
    private int age;
    private String gender;//性别 MALE FEMALE PRIVATE

    @JSONField(format = "yyyy-MM-dd")
    private Date birthDate;

    private int paceNum;
    private int journey;//旅程，单位km

    @JSONField(format = "yyyy-MM-dd")
    private Date registerDate;//注册日期

    private String signatureText;//个性签名

    private Integer countryId;
    private Integer provinceId;
    private String province;
    private Integer cityId;
    private String city;

    private boolean certificated;//true 表示为认证用户

    private String accessToken;

    private String openId;

    /**
     * 认证类型
     */
    private String authType;//QQ,WeChat,Weibo

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public boolean isCertificated() {
        return certificated;
    }

    public void setCertificated(boolean certificated) {
        this.certificated = certificated;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPaceNum() {
        return paceNum;
    }

    public void setPaceNum(int paceNum) {
        this.paceNum = paceNum;
    }

    public int getJourney() {
        return journey;
    }

    public void setJourney(int journey) {
        this.journey = journey;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSignatureText() {
        return signatureText;
    }

    public void setSignatureText(String signatureText) {
        this.signatureText = signatureText;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
