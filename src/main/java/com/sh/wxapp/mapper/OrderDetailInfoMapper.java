package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.OrderDetailInfo;

public interface OrderDetailInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetailInfo record);

    int insertSelective(OrderDetailInfo record);

    OrderDetailInfo selectByPrimaryKey(Long id);

    OrderDetailInfo selectByOrderId(Long orderId);

    int updateByPrimaryKeySelective(OrderDetailInfo record);

    int updateByPrimaryKey(OrderDetailInfo record);
}