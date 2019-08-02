package com.sh.wxapp.controller;

import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.user.UserListDTO;
import com.sh.wxapp.dto.user.UserListQueryDTO;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author xuqie
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    public JsonResponse<PageableDTO<UserListDTO>> getUserList(@RequestBody UserListQueryDTO userListQueryDTO){
        UserListQueryDTO queryDTO=new UserListQueryDTO();
        BeanUtils.copyProperties(userListQueryDTO,queryDTO);
        PageableDTO<UserListDTO> pageableDTO= userService.getUserList(queryDTO);
        return JsonResponse.success(pageableDTO);
    }
}
