package com.sh.wxapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.order.LiveOrderListQueryDTO;
import com.sh.wxapp.dto.order.OrderInfoDTO;
import com.sh.wxapp.dto.order.OrderInsertUpdateDTO;
import com.sh.wxapp.enm.BusinessExceptionCodeEnum;
import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.req.order.LiveOrderListQueryReq;
import com.sh.wxapp.req.order.OrderAddReq;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.OrderService;
import com.sh.wxapp.util.SsoUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    @ApiModelProperty("发单(单个发送)")
    public JsonResponse addOrder(@RequestBody OrderAddReq req){
        Long userId=SsoUtil.getCurrentUser().getUserId();
        List<OrderInsertUpdateDTO> orderInsertUpdateDTOS= Lists.newArrayList();

        OrderInsertUpdateDTO orderInsertUpdateDTO=new OrderInsertUpdateDTO();
        BeanUtils.copyProperties(req,orderInsertUpdateDTO);
        orderInsertUpdateDTOS.add(orderInsertUpdateDTO);
        try {
            orderService.addOrders(orderInsertUpdateDTOS,userId);
        }catch (Exception e){
            log.info("addOrderException--req:{},exception:{}", JSONObject.toJSONString(req),JSONObject.toJSONString(e));
            throw new BusinessException(BusinessExceptionCodeEnum.ADD_FAIL.getCode(),e.getMessage());
        }
        return JsonResponse.success("查询成功",null);
    }


    @RequestMapping(value = "/getIssuedOrders",method = RequestMethod.POST)
    @ApiModelProperty("获取已发布的订单（即可抢的有效单）")
    public JsonResponse<PageableDTO<List<OrderInfoDTO>>> getIssuedOrders(@RequestBody LiveOrderListQueryReq req){
        Integer positionCode=SsoUtil.getCurrentUser().getPosition();
        LiveOrderListQueryDTO dto=new LiveOrderListQueryDTO();
        BeanUtils.copyProperties(req,dto);
        PageableDTO<List<OrderInfoDTO>> listPageableDTO= orderService.getIssuedOrders(positionCode,dto);
        return JsonResponse.success("查询成功",listPageableDTO);
    }




}
