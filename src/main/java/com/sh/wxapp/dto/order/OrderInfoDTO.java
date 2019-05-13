package com.sh.wxapp.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @: 2019-03-05 15:24
 **/
public class OrderInfoDTO {

    @ApiModelProperty("orderInfoId")
    private Long id;
    @ApiModelProperty("订单号")
    private String orderNo;
    @JsonIgnore
    @ApiModelProperty("订单类型")
    private Integer orderType;

    @ApiModelProperty("订单类型")
    private String orderTypeDisplay;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerTotal() {
        return passengerTotal;
    }

    public void setPassengerTotal(Integer passengerTotal) {
        this.passengerTotal = passengerTotal;
    }

    public Integer getLuggageTotal() {
        return luggageTotal;
    }

    public void setLuggageTotal(Integer luggageTotal) {
        this.luggageTotal = luggageTotal;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getPassengerContact() {
        return passengerContact;
    }

    public void setPassengerContact(String passengerContact) {
        this.passengerContact = passengerContact;
    }

    public String getPassengerWechat() {
        return passengerWechat;
    }

    public void setPassengerWechat(String passengerWechat) {
        this.passengerWechat = passengerWechat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderTypeDisplay() {
        return orderTypeDisplay;
    }

    public void setOrderTypeDisplay(String orderTypeDisplay) {
        this.orderTypeDisplay = orderTypeDisplay;
    }
}
