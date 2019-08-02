package com.sh.wxapp.serviceimp.user;

import com.sh.wxapp.domain.AccountInfo;
import com.sh.wxapp.domain.UserInfo;
import com.sh.wxapp.dto.user.UserInfoDTO;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.enm.PositionEnum;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.mapper.AccountInfoMapper;
import com.sh.wxapp.mapper.UserInfoMapper;
import com.sh.wxapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * UserServiceImp
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Service("accountService")
public class AccountServiceImp implements AccountService {

    private static final String DEFAULT_NAME_PREFIX="用户";

    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger log = LoggerFactory.getLogger(AccountServiceImp.class);

    @Override
    public UserInfoDTO getAccount(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(username)) {
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(), "请输入账户名和密码");
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        AccountInfo accountInfo = null;
        if ((accountInfo = accountInfoMapper.selectByUsernamePassWord(username, password)) != null) {
            Optional.of(userInfoMapper.selectByUserId(accountInfo.getId()))
                    .ifPresent(userInfo -> {
                        BeanUtils.copyProperties(userInfo, userInfoDTO);
                    });
            return userInfoDTO;
        } else {
            throw new BusinessException(BusinessExceptionCodeEnum.NO_RESULT_FOUND.getCode(), "用户不存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long register(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userName)) {
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(), "账户或密码不能为空");
        }
        if (StringUtils.isEmpty(userName.trim()) || StringUtils.isEmpty(userName.trim())) {
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(), "账户或密码不能为空");
        }
        //查看账户是否已存在
        if (accountInfoMapper.selectByUsername(userName) != null) {
            throw new BusinessException(BusinessExceptionCodeEnum.NORMAL.getCode(), "账户已存在");
        }
        //新添加账户
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUsername(userName);
        accountInfo.setPassword(password);
        accountInfoMapper.insertSelective(accountInfo);
        Long userId=null;
        if ((userId=accountInfo.getId()) == null) {
            throw new BusinessException(BusinessExceptionCodeEnum.NORMAL.getCode(), "添加账户失败");
        }
        //添加默认账户名
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(accountInfo.getId());
        userInfo.setName(DEFAULT_NAME_PREFIX+userId);
        userInfo.setPosition(PositionEnum.VISITOR.getCode());
        userInfoMapper.insertSelective(userInfo);
        return userId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateAccountInfo(Long userId, UserInfoDTO userInfoDTO){
        if(userId==null){
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(),"userId为空");
        }
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        userInfoMapper.updateByUserIdSelective(userInfo);
    }



}
