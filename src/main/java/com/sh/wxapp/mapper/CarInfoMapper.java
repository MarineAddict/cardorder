package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.CarInfo;

import java.util.List;

public interface CarInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(Long id);

    List<CarInfo> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);
}