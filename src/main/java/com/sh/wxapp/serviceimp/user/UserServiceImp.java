package com.sh.wxapp.serviceimp.user;

import com.sh.wxapp.domain.AccountInfo;
import com.sh.wxapp.domain.UserInfo;
import com.sh.wxapp.dto.UserInfoDTO;
import com.sh.wxapp.enm.BusinessExceptionCode;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.mapper.AccountInfoMapper;
import com.sh.wxapp.mapper.UserInfoMapper;
import com.sh.wxapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * UserServiceImp
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Service("userService")
public class UserServiceImp implements UserService {
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    public UserInfoDTO getUser(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(username)) {
            throw new BusinessException(BusinessExceptionCode.PARAMETER_NULL.getCode(), "请输入账户名和密码");
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        AccountInfo accountInfo = null;
        if ((accountInfo = accountInfoMapper.selectByUsernamePassWord(username, password)) != null) {
            Optional.of(userInfoMapper.selectByUserId(accountInfo.getUserId()))
                    .ifPresent(userInfo -> {
                        BeanUtils.copyProperties(userInfo, userInfoDTO);
                    });
            return userInfoDTO;
        } else {
            throw new BusinessException(BusinessExceptionCode.NO_RESULT_FOUND.getCode(), "用户不存在");
        }
    }
}
