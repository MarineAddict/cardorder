package com.sh.wxapp.serviceimp.user;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.user.UserListDTO;
import com.sh.wxapp.dto.user.UserListQueryDTO;
import com.sh.wxapp.enm.GenderEnum;
import com.sh.wxapp.enm.PositionEnum;
import com.sh.wxapp.mapper.UserInfoMapper;
import com.sh.wxapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author xuqie
 * @version 1.0.0
 **/

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
private UserInfoMapper userInfoMapper;

    @Override
    public PageableDTO<UserListDTO> getUserList(UserListQueryDTO queryDTO) {
        log.info("getUserList--query:{}", JSONObject.toJSONString(queryDTO));
        List<UserListDTO> list= Lists.newArrayList();
        PageInfo<UserListDTO> info=PageHelper.startPage(queryDTO.getPageNum(),queryDTO.getPageSize(),true).doSelectPageInfo(()->{
            Optional.ofNullable(userInfoMapper.getUserList(queryDTO))
                    .ifPresent(userInfos -> {
                        userInfos.forEach(userInfo -> {
                            UserListDTO userListDTO=new UserListDTO();
                            BeanUtils.copyProperties(userInfo,userListDTO);
                            userListDTO.setPositionName(PositionEnum.getPositionByCode(userInfo.getPosition()).getValue());
                            userListDTO.setGenderDesc(GenderEnum.getByCode(userInfo.getGender()).getValue());
                            list.add(userListDTO);
                        });
                    });
        });
        info.setList(list);
        PageableDTO<UserListDTO> dto=new PageableDTO<>(info);
        return dto;
    }
}
