package com.sh.wxapp.util;

import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-05-28 01:06
 **/
public class CookieUtils {

    public static void removeCookie(HttpServletRequest request, String cookieName){
        if(request.getCookies().length>0){
            for (Cookie cookie :request.getCookies()) {
                if(cookieName.equals(cookie.getName())){
                    cookie.setPath("/");
                    //删除
                    cookie.setMaxAge(0);
                }
            }
        }
    }

}
