package com.sh.wxapp.util;

import com.sh.wxapp.dto.user.UserInfoDTO;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-27 10:15
 **/
public class SsoUtil {

    private SsoUtil(){
    }
    /*存用户信息*/
     private static final ThreadLocal<UserInfoDTO> USER_LOCAL=new ThreadLocal<UserInfoDTO>();

    public static void set(UserInfoDTO user){
        USER_LOCAL.set(user);
    }

    public static UserInfoDTO getCurrentUser(){
        return USER_LOCAL.get();
    }

    public static void remove(){
        USER_LOCAL.remove();
    }


}
