package com.sh.wxapp.util;

import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-05-28 01:06
 **/
public class CookieUtils {

    public static final int COOKIE_AGE=10*60;

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName){
        if(request.getCookies()!=null) {
            //判空一次避免小程序
            if (request.getCookies().length > 0) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookieName.equals(cookie.getName())) {
                        //删除
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }

    public static void addCookie(HttpServletResponse response, String cookieName,String cookieValue){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_AGE);
        response.addCookie(cookie);
    }

}
