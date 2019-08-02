package com.sh.wxapp.req.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * OrderAddReq
 *
 * @author xuqie
 * @version 1.0.0
 **/
@ApiModel
@Data
public class OrderAddReq {

    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("订单类型")
    private Integer orderType;
    @ApiModelProperty("用车时间")
    private String time;
    @ApiModelProperty("乘客姓名")
    private String passengerName;
    @ApiModelProperty("乘客人数")
    private Integer passengerTotal;
    @ApiModelProperty("行李数")
    private Integer luggageTotal;
    @ApiModelProperty("起始点")
    private String departure;
    @ApiModelProperty("终点")
    private String destination;
    @ApiModelProperty("航班号")
    private String flightNo;
    @ApiModelProperty("乘客联系方式")
    private String passengerContact;
    @ApiModelProperty("乘客微信")
    private String passengerWechat;
    @ApiModelProperty("价格")
    private BigDecimal price;
}
