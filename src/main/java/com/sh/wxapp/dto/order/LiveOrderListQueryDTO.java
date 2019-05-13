package com.sh.wxapp.dto.order;

import com.sh.wxapp.dto.PageableQueryDTO;

import java.util.Date;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-05 16:49
 **/
public class LiveOrderListQueryDTO extends PageableQueryDTO {

    private String orderNo;

    private Integer orderType;

    private Date createStartDate;

    private Date createEndDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getCreateStartDate() {
        return createStartDate;
    }

    public void setCreateStartDate(Date createStartDate) {
        this.createStartDate = createStartDate;
    }

    public Date getCreateEndDate() {
        return createEndDate;
    }

    public void setCreateEndDate(Date createEndDate) {
        this.createEndDate = createEndDate;
    }

}
