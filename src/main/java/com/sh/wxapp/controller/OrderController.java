package com.sh.wxapp.controller;

import com.sh.wxapp.dto.PageableDTO;
import com.sh.wxapp.dto.order.LiveOrderListQueryDTO;
import com.sh.wxapp.dto.order.OrderInfoDTO;
import com.sh.wxapp.req.order.LiveOrderListQueryReq;
import com.sh.wxapp.rop.JsonResponse;
import com.sh.wxapp.service.OrderService;
import com.sh.wxapp.util.SsoUtil;
import io.swagger.annotations.ApiModelProperty;
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
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getIssuedOrders",method = RequestMethod.POST)
    @ApiModelProperty("获取已发布的订单（即可抢的有效单）")
    public JsonResponse getIssuedOrders(@RequestBody LiveOrderListQueryReq req){
        Integer positionCode=SsoUtil.getCurrentUser().getPosition();
        LiveOrderListQueryDTO dto=new LiveOrderListQueryDTO();
        BeanUtils.copyProperties(req,dto);
        PageableDTO<List<OrderInfoDTO>> listPageableDTO= orderService.getIssuedOrders(positionCode,dto);
        return JsonResponse.success("查询成功",listPageableDTO);
    }

}
