package com.sh.wxapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.order.LiveOrderListQueryDTO;
import com.sh.wxapp.dto.order.OrderInfoDTO;
import com.sh.wxapp.dto.order.OrderInsertUpdateDTO;
import com.sh.wxapp.dto.order.OrderListQueryDTO;
import com.sh.wxapp.dto.user.UserInfoDTO;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.req.order.LiveOrderListQueryReq;
import com.sh.wxapp.req.order.OrderAddReq;
import com.sh.wxapp.req.order.OrderListReq;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.OrderService;
import com.sh.wxapp.service.UserService;
import com.sh.wxapp.util.SsoUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-14 21:40
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    @ApiModelProperty("订单列表（web）")
    public JsonResponse getOrderList(@RequestBody OrderListReq req) {
        log.info("getOrderList--req:{}", JSONObject.toJSONString(req));
        Long userId = SsoUtil.getCurrentUser().getUserId();
        OrderListQueryDTO orderListQueryDTO = new OrderListQueryDTO();
        BeanUtils.copyProperties(req, orderListQueryDTO);
        PageableDTO<OrderInfoDTO> pageableDTO = orderService.getOrderList(orderListQueryDTO);
        Optional.ofNullable(pageableDTO.getList()).ifPresent(orderInfoDTOS -> {
            orderInfoDTOS.forEach(orderInfoDTO -> {
               Long createId= orderInfoDTO.getCreateId();
               Long orderTaker=orderInfoDTO.getOrderTaker();
                Optional.ofNullable(userService.getUserInfo(createId)).ifPresent(userInfoDTO -> orderInfoDTO.setCreateName(userInfoDTO.getName()));
                Optional.ofNullable(userService.getUserInfo(orderTaker)).ifPresent(userInfoDTO -> orderInfoDTO.setCreateName(userInfoDTO.getName()));
            });
        });
        return JsonResponse.success("查询成功", pageableDTO);
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ApiModelProperty("发单(单个发送)")
    public JsonResponse addOrder(@Valid @RequestBody OrderAddReq req, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BusinessException(BusinessExceptionCodeEnum.PARAMETER_NULL.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        Long userId = SsoUtil.getCurrentUser().getUserId();
        List<OrderInsertUpdateDTO> orderInsertUpdateDTOS = Lists.newArrayList();

        OrderInsertUpdateDTO orderInsertUpdateDTO = new OrderInsertUpdateDTO();
        BeanUtils.copyProperties(req, orderInsertUpdateDTO);
        orderInsertUpdateDTOS.add(orderInsertUpdateDTO);
        try {
            orderService.addOrders(orderInsertUpdateDTOS, userId);
        } catch (Exception e) {
            log.info("addOrderException--req:{},exception:{}", JSONObject.toJSONString(req), JSONObject.toJSONString(e));
            throw new BusinessException(BusinessExceptionCodeEnum.ADD_FAIL.getCode(), e.getMessage());
        }
        return JsonResponse.success("添加订单成功", null);
    }

    @RequestMapping(value = "/removeOrder/{orderId}", method = RequestMethod.GET)
    @ApiModelProperty("撤单(单个发送)")
    public JsonResponse removeOrder(@PathVariable("orderId") Long orderId){
        Long userId = SsoUtil.getCurrentUser().getUserId();
        List<Long> orderIds=Lists.newArrayList();
        orderIds.add(orderId);
        try {
            orderService.removeOrders(orderIds,userId);
        } catch (Exception e) {
            log.info("removeOrderException--orderId:{},exception:{}", orderId, JSONObject.toJSONString(e));
            throw new BusinessException(BusinessExceptionCodeEnum.UPDATE_FAIL.getCode(), e.getMessage());
        }
        return JsonResponse.success("删除订单成功", null);
    }


    @RequestMapping(value = "/getIssuedOrders", method = RequestMethod.POST)
    @ApiModelProperty("获取已发布的订单（即可抢的有效单）")
    public JsonResponse<PageableDTO<OrderInfoDTO>> getIssuedOrders(@RequestBody LiveOrderListQueryReq req) {
        Integer positionCode = SsoUtil.getCurrentUser().getPosition();
        LiveOrderListQueryDTO dto = new LiveOrderListQueryDTO();
        BeanUtils.copyProperties(req, dto);
        try {
            PageableDTO<OrderInfoDTO> listPageableDTO = orderService.getIssuedOrders(positionCode, dto);
            return JsonResponse.success("查询成功", listPageableDTO);
        }catch (Exception e){
            throw new BusinessException(BusinessExceptionCodeEnum.NORMAL);
        }
    }

    @RequestMapping(value = "/placeOrder/{orderId}", method = RequestMethod.POST)
    @ApiModelProperty("获取已发布的订单（即可抢的有效单）")
    public JsonResponse placeOrder(@PathVariable("orderId") Long orderId) {
        Long userId = SsoUtil.getCurrentUser().getUserId();
        try {
            orderService.placeOrder(orderId, userId);
            return JsonResponse.success("抢单成功", null);
        }
        catch (BusinessException be){
            return JsonResponse.success(BusinessExceptionCodeEnum.ORDER_TAKEN_ALREADY.getMsg(), null);
        }
        catch (Exception e){
            throw new BusinessException(BusinessExceptionCodeEnum.NORMAL);
        }
    }



}
