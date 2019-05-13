package com.sh.wxapp.service;

import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.order.OrderInfoDTO;
import com.sh.wxapp.dto.order.OrderInsertUpdateDTO;
import com.sh.wxapp.dto.order.LiveOrderListQueryDTO;
import com.sh.wxapp.enm.PositionEnum;

import java.util.List;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-04 14:00
 **/
public interface OrderService {

    /**
     * 添加订单
     * @param orders
     * @param userId
     */
   void addOrders(List<OrderInsertUpdateDTO> orders, Long userId);

    /**
     * 删除订单
     * @param
     */
    void removeOrders(List<Long> orderId);

    /**
     * 修改订单
     * @param orderInsertUpdateDTO
     */
    void modifyOrder(OrderInsertUpdateDTO orderInsertUpdateDTO);

    /**
     * 下单抢单
     * @param orderId
     * @param userId
     */
    void placeOrder(Long orderId,Long userId);

    /**
    * @Description: 取消订单(已抢单的取消)
    * @Param:
    * @return:
    * @Author: xuqie
    * @Date: 2019/3/4
    */
    void cancelOrder(Long orderId);


    /**
    * @Description:查询已发布的全部订单,根据人员
    * @Param:
    * @return:
    * @Author: xuqie
    * @Date: 2019/3/5
    */
    PageableDTO<List<OrderInfoDTO>> getIssuedOrders(Integer positionCode, LiveOrderListQueryDTO liveOrderListQueryDTO);

}
