package com.sh.wxapp.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * UserInfoDTO
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Data
public class UserInfoDTO implements Serializable {

    private Long userId;

    private String name;

    private Integer gender;

    private String genderDesc;

    private Integer position;

    private String positionName;

    private String telephone;

    private String wechatNum;

    private String qqNum;

}
