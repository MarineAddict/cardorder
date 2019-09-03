package com.sh.wxapp.mapper;

import com.sh.wxapp.domain.OrderInfo;
import com.sh.wxapp.dto.order.LiveOrderListQueryDTO;
import com.sh.wxapp.dto.order.OrderListQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> selectIssuedOrder(LiveOrderListQueryDTO liveOrderListQueryDTO);

    int updateByPrimaryKeySelective(@Param("OrderInfo") OrderInfo record,@Param("oldVersion") Long oldVersion);

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> selectOrderList(OrderListQueryDTO orderListQueryDTO);
}