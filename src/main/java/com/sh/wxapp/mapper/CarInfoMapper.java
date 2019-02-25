package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.CarInfo;

public interface CarInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);
}