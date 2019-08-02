package com.sh.wxapp.service;

import com.sh.wxapp.dto.user.UserInfoDTO;

/**
 * UserService
 *
 * @author xuqie
 * @version 1.0.0
 **/

public interface AccountService {

    /*账号密码获得用户*/
    UserInfoDTO getAccount(String username, String password);

    /*账号密码注册*/
    Long register(String userName,String password);

    /*更新个人信息*/
    void updateAccountInfo(Long userId, UserInfoDTO userInfoDTO);
}
