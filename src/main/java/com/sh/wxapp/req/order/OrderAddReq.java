package com.sh.wxapp.req.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "订单号不能为空")
    private String orderNo;
    @ApiModelProperty("订单类型")
    @NotNull(message = "订单类型必填")
    private Integer orderType;
    @ApiModelProperty("用车时间")
    @NotEmpty(message = "用车时间不能为空")
    private String time;
    @ApiModelProperty("乘客姓名")
    @NotEmpty(message = "乘客姓名不能为空")
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
    @NotNull(message = "价格不能为空")
    private BigDecimal price;
}
