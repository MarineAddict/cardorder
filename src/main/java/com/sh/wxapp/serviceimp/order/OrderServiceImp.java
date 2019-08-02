package com.sh.wxapp.serviceimp.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh.wxapp.domain.OrderDetailInfo;
import com.sh.wxapp.domain.OrderInfo;
import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.order.OrderInfoDTO;
import com.sh.wxapp.dto.order.OrderInsertUpdateDTO;
import com.sh.wxapp.dto.order.LiveOrderListQueryDTO;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.enm.OrderStatusEnum;
import com.sh.wxapp.enm.PositionEnum;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.mapper.OrderDetailInfoMapper;
import com.sh.wxapp.mapper.OrderInfoMapper;
import com.sh.wxapp.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
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
    @Transactional(rollbackFor = BusinessException.class)
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
            orderInfo.setCreateId(userId);
            orderInfoMapper.insertSelective(orderInfo);
            if(orderInfo.getId()==null){
                throw new BusinessException(BusinessExceptionCodeEnum.NORMAL.getCode(),"插入时异常");
            }
            OrderDetailInfo orderDetailInfo=new OrderDetailInfo();
            orderDetailInfo.setOrderId(orderInfo.getId());
            BeanUtils.copyProperties(orderInsertUpdateDTO,orderDetailInfo);
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
                            Long oldVersion=orderInfo.getVersion();
                            Long newVersion=oldVersion+1;
                            //加入版本号
                            orderInfo.setVersion(newVersion);
                            if(orderInfoMapper.updateByPrimaryKeySelective(orderInfo,oldVersion)==0){
                                //如果更新失败
                               throw new BusinessException(BusinessExceptionCodeEnum.UPDATE_FAIL.getCode(),"更新失败");
                            }
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
        OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderInsertUpdateDTO.getId());
        if(!OrderStatusEnum.ISSUED.getCode().equals(orderInfo.getStatus())){
            throw new BusinessException(BusinessExceptionCodeEnum.NORMAL.getCode(),"该订单无法被修改");
        }
        Long oldVersion=orderInfo.getVersion();
        Long newVersion=oldVersion+1;

        BeanUtils.copyProperties(orderInsertUpdateDTO,orderInfo);
        orderInfo.setVersion(newVersion);
        if(orderInfoMapper.updateByPrimaryKeySelective(orderInfo,oldVersion)==0){
            //如果更新失败
            throw new BusinessException(BusinessExceptionCodeEnum.UPDATE_FAIL.getCode(),"更新失败");
        }
        Optional.of(orderDetailInfoMapper.selectByOrderId(orderInsertUpdateDTO.getId()))
                .ifPresent(orderDetailInfo -> {
                    BeanUtils.copyProperties(orderInsertUpdateDTO,orderDetailInfo);
                    orderDetailInfo.setOrderId(orderInsertUpdateDTO.getId());
                    orderDetailInfoMapper.updateByPrimaryKeySelective(orderDetailInfo);
                });
    }

    @Override
    public void placeOrder(Long orderId, Long userId) {
        if(orderId==null||userId==null){
            return;
        }
        OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderId);
        Long oldVersion=orderInfo.getVersion();
        Long newVersion=orderInfo.getVersion()+1;

        orderInfo.setOrderTaker(userId);
        orderInfo.setStatus(OrderStatusEnum.TAKEN.getCode());
        orderInfo.setTakenTime(new Date());
        orderInfo.setVersion(newVersion);
        if(orderInfoMapper.updateByPrimaryKeySelective(orderInfo,oldVersion)==0){
            //更新失败说明被抢
            throw new BusinessException(BusinessExceptionCodeEnum.ORDER_TAKEN_ALREADY);
        }
    }

    @Override
    public void cancelOrder(Long orderId) {
        OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(orderId);
        if(OrderStatusEnum.TAKEN.getCode().equals(orderInfo.getStatus())){
            Long oldVersion=orderInfo.getVersion();
            Long newVersion=oldVersion+1;
            orderInfo.setVersion(newVersion);
            orderInfo.setStatus(OrderStatusEnum.ISSUED.getCode());
            orderInfoMapper.updateByPrimaryKeySelective(orderInfo,oldVersion);
        }
    }

    @Override
    public PageableDTO<List<OrderInfoDTO>> getIssuedOrders(Integer positionCode, LiveOrderListQueryDTO liveOrderListQueryDTO) {
        if(positionCode ==null){
            return null;
        }
        //游客不允许查看订单
        if(PositionEnum.VISITOR.getCode().equals(positionCode)){
            return null;
        }
        List<OrderInfoDTO> list=new ArrayList<>();
        PageInfo<OrderInfoDTO> info=PageHelper.startPage(liveOrderListQueryDTO.getPageNum(),liveOrderListQueryDTO.getPageSize(),true)
                .doSelectPageInfo(()->{
                    Optional.ofNullable(orderInfoMapper.selectIssuedOrder(liveOrderListQueryDTO))
                            .ifPresent(orderInfos -> {
                                orderInfos.forEach(orderInfo -> {
                                    OrderInfoDTO orderInfoDTO=new OrderInfoDTO();
                                    BeanUtils.copyProperties(orderInfo,orderInfoDTO);
                                    list.add(orderInfoDTO);
                                });
                            });
        });
        info.setList(list);
        PageableDTO<List<OrderInfoDTO>> dto=new PageableDTO<>(info);
        return dto;
    }

}
