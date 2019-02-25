package com.sh.wxapp.service;

import com.sh.wxapp.dto.user.UserInfoDTO;

/**
 * UserService
 *
 * @author xuqie
 * @version 1.0.0
 **/

public interface UserService {

    /*账号密码获得用户*/
    UserInfoDTO getUser(String username,String password);

    /*账号密码注册*/
    Long register(String userName,String password);

    /*更新个人信息*/
    void updateUserInfo(Long userId,UserInfoDTO userInfoDTO);
}
