package com.sh.wxapp.controller;

import com.sh.wxapp.dto.UserInfoDTO;
import com.sh.wxapp.jwt.TokenUtils;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-23 18:26
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResponse login(String username, String password) {
        UserInfoDTO userInfoDTO = userService.getUser(username, password);
        Map map = new HashMap();
        map.put(TokenUtils.EXPIRETIME, new Date().getTime() + TokenUtils.TOKEN_VALID_TIME);
        map.put(TokenUtils.USERID, userInfoDTO.getUserId());
        String token = TokenUtils.createToken(map);
        Map mp = new HashMap();
        mp.put("token", token);
        mp.put("userInfoDTO", userInfoDTO);
        return JsonResponse.success("登陆成功", mp);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("注册")
    public JsonResponse register(String username, String password) {
        return null;
    }

}
