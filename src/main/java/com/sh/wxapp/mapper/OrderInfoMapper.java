package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> selectIssuedOrder();

    int updateByPrimaryKeySelective(@Param("OrderInfo") OrderInfo record,@Param("oldVersion") Long oldVersion);

    int updateByPrimaryKey(OrderInfo record);
}