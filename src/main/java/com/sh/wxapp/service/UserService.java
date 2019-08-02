package com.sh.wxapp.service;

import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.user.UserListDTO;
import com.sh.wxapp.dto.user.UserListQueryDTO;

/**
 * UserService
 *
 * @author xuqie
 * @version 1.0.0
 **/

public interface UserService {

    PageableDTO<UserListDTO> getUserList(UserListQueryDTO queryDTO);

}
