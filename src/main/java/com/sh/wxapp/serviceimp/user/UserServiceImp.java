package com.sh.wxapp.serviceimp.user;

import com.sh.wxapp.dto.UserInfoDTO;
import com.sh.wxapp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImp
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Service("userService")
public class UserServiceImp implements UserService {
    public UserInfoDTO getUser(String username, String password) {
        return null;
    }
}
