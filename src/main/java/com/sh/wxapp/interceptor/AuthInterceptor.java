package com.sh.wxapp.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.jwt.TokenUtils;
import com.sh.wxapp.rop.JsonResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthInterceptor
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Component
public class AuthInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //token校验
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            Map<String, Object> result = TokenUtils.parseToken(token);
            if (result.get(TokenUtils.RESULT).equals(TokenUtils.TOKEN_PASS)) {
                //通过,验证时间是否发新token
                if (result.get(TokenUtils.EXPIRETIME) != null) {
                    response.setHeader("token", token);
                    Long expDate = Long.valueOf(result.get(TokenUtils.EXPIRETIME).toString());
                    Long now = new Date().getTime();
                    //过期时间小于有效的时间
                    if (expDate - now <= TokenUtils.TOKEN_REFRESH_TIME) {
                        Object userId = result.get(TokenUtils.USERID);
                        Map map = new HashMap();
                        map.put(TokenUtils.USERID, userId);
                        TokenUtils.createToken(map);
                        response.setHeader("token", token);
                    }
                }
                return true;
            } else {
                //过期或者验证失败
                JsonResponse jsonResponse=JsonResponse.fail(BusinessExceptionCodeEnum.TOKEN_ERROR.getCode(),"验证失败");
                response.getWriter().write(JSONObject.toJSONString(jsonResponse));
                return false;
            }
        } else {
            //无token
            //1.查验身份
            JsonResponse jsonResponse=JsonResponse.fail(BusinessExceptionCodeEnum.TOKEN_ERROR.getCode(),"验证失败");
            response.getWriter().write(JSONObject.toJSONString(jsonResponse));
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
