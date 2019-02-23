package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.AccountInfo;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Long id);

    AccountInfo selectByUsernamePassWord(@Param("username") String username, @Param("password")String password);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}