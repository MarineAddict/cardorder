package com.sh.wxapp.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.sh.wxapp.dto.user.UserInfoDTO;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.jwt.TokenUtils;
import com.sh.wxapp.rop.JsonResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //token校验
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            //可能不在head，查询cookies
            Cookie[] cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        //再次判断token是否存在
        if (!StringUtils.isEmpty(token)) {
            //测试使用,直接通过
            if(token.equals("testToken")){
                return true;
            }
            //存在cookie
            Map<String, Object> result = TokenUtils.parseToken(token);
            //token经过解析后得到分析结果为通过
            if (result.get(TokenUtils.RESULT).equals(TokenUtils.TOKEN_PASS)) {
                //加入逻辑，需要查看session中是否有userInfo，没有说明当前的人员信息丢失，依然需要重新登陆
                UserInfoDTO userInfoDTO = (UserInfoDTO)request.getSession().getAttribute("userInfo");
                if(userInfoDTO==null){
                    //人员为空信息，直接返回登陆
                    response.sendRedirect("/loginPage/index");
                    return false;
                }
                //通过,验证时间是否发新token
                if (result.get(TokenUtils.EXPIRETIME) != null) {
                    response.setHeader("token", token);
                    Long expDate = Long.valueOf(result.get(TokenUtils.EXPIRETIME).toString());
                    Long now = System.currentTimeMillis();
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
                response.sendRedirect("/loginPage/index");
                JsonResponse jsonResponse=JsonResponse.fail(BusinessExceptionCodeEnum.TOKEN_ERROR.getCode(),"验证失败");
                response.getWriter().write(JSONObject.toJSONString(jsonResponse));
                return false;
            }
        } else {
            //无token
            //1.查验身份
            response.sendRedirect("/loginPage/index");
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
