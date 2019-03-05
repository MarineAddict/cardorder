package com.sh.wxapp.dto.order;

import java.util.Date;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-05 16:49
 **/
public class OrderListQueryDTO {

    private String orderNo;

    private Integer orderType;

    private Integer status;

    private Date createStartDate;

    private Date createEndDate;

    private Long orderTaker;

    private Date takenTimeStartDate;

    private Date takenTimeEndDate;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(Long orderTaker) {
        this.orderTaker = orderTaker;
    }

    public Date getTakenTimeStartDate() {
        return takenTimeStartDate;
    }

    public void setTakenTimeStartDate(Date takenTimeStartDate) {
        this.takenTimeStartDate = takenTimeStartDate;
    }

    public Date getTakenTimeEndDate() {
        return takenTimeEndDate;
    }

    public void setTakenTimeEndDate(Date takenTimeEndDate) {
        this.takenTimeEndDate = takenTimeEndDate;
    }
}
