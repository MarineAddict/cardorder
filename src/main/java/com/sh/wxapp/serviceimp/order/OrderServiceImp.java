package com.sh.wxapp.serviceimp.order;

import com.sh.wxapp.domain.OrderDetailInfo;
import com.sh.wxapp.domain.OrderInfo;
import com.sh.wxapp.dto.order.OrderInsertUpdateDTO;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.enm.OrderStatusEnum;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.mapper.OrderDetailInfoMapper;
import com.sh.wxapp.mapper.OrderInfoMapper;
import com.sh.wxapp.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-04 14:06
 **/
@Service("orderService")
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderDetailInfoMapper orderDetailInfoMapper;

    @Override
    public void addOrders(List<OrderInsertUpdateDTO> orders, Long userId) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        if (userId == null) {
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(), "用户id为空");
        }
        orders.forEach(orderInsertUpdateDTO -> {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setStatus(OrderStatusEnum.ISSUED.getCode());
            BeanUtils.copyProperties(orderInsertUpdateDTO, orderInfo);
            orderInfoMapper.insertSelective(orderInfo);
            if(orderInfo.getId()==null){
                throw new BusinessException(BusinessExceptionCodeEnum.NORMAL.getCode(),"插入时异常");
            }
            OrderDetailInfo orderDetailInfo=new OrderDetailInfo();
            orderDetailInfo.setOrderId(orderInfo.getId());
            BeanUtils.copyProperties(orderInsertUpdateDTO,orderInfo);
            orderDetailInfoMapper.insertSelective(orderDetailInfo);
        });
    }

    @Override
    public void removeOrders(List<Long> orderIds) {
        if(CollectionUtils.isEmpty(orderIds)){
            return;
        }
        //置掉
        orderIds.forEach(id->{
            Optional.of(orderInfoMapper.selectByPrimaryKey(id))
                    .ifPresent(orderInfo -> {
                        //如果已接单，无法取消;如果已完成，也无法取消
                        //发布情况下才删除
                        if(OrderStatusEnum.ISSUED.getCode().equals(orderInfo.getStatus())){
                            orderInfo.setStatus(OrderStatusEnum.CANCELED.getCode());
                            orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
                        }
                    });
        });
    }

    @Override
    public void modifyOrder(OrderInsertUpdateDTO orderInsertUpdateDTO) {
        if(orderInsertUpdateDTO==null){
            return;
        }
        if(orderInsertUpdateDTO.getId()==null){
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(),"orderId为空");
        }
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderInsertUpdateDTO,orderInfo);
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        Optional.of(orderDetailInfoMapper.selectByOrderId(orderInsertUpdateDTO.getId()))
                .ifPresent(orderDetailInfo -> {
                    BeanUtils.copyProperties(orderInsertUpdateDTO,orderDetailInfo);
                    orderDetailInfo.setOrderId(orderInsertUpdateDTO.getId());
                    orderDetailInfoMapper.updateByPrimaryKeySelective(orderDetailInfo);
                });
    }

    @Override
    public void placeOrder(Long orderId, Long userId) {

    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
