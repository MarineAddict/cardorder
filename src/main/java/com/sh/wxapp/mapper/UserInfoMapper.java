package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.UserInfo;
import com.sh.wxapp.dto.user.UserListQueryDTO;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    UserInfo selectByUserId(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByUserIdSelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> getUserList(UserListQueryDTO queryDTO);
}