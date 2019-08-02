package com.sh.wxapp.dto.user;

import com.sh.wxapp.dto.PageableQueryDTO;
import lombok.Data;

/**
 * UserListQueryDTO
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Data
public class UserListQueryDTO extends PageableQueryDTO {

    private Long userId;
    private String name;

}
