package com.sh.wxapp.req.user;

import io.swagger.annotations.ApiModel;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-25 14:14
 **/
@ApiModel("账户信息更新")
public class UserInfoUpdateRequest {
    private Long userId;

    private String name;

    private Integer gender;

    private Integer position;

    private String telephone;

    private String wechatNum;

    private String qqNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
}
