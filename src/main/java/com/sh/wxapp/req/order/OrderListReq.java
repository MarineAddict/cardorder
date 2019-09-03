package com.sh.wxapp.req.order;

import com.sh.wxapp.req.PageableRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * OrderListReq
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Data
@ApiModel
public class OrderListReq extends PageableRequest {

    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("订单号")
    private Integer orderType;
    @ApiModelProperty("订单状态")
    private Integer status;
    @ApiModelProperty("发布起始日")
    private Date createStartDate;
    @ApiModelProperty("发布结束日")
    private Date createEndDate;
}
