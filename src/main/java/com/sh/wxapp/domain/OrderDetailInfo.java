package com.sh.wxapp.domain;

import java.io.Serializable;

public class OrderDetailInfo implements Serializable {
    private Long id;

    private Long orderId;

    private String time;

    private String passengerName;

    private Integer passengerTotal;

    private Integer luggageTotal;

    private String departure;

    private String destination;

    private String flightNo;

    private String passengerContact;

    private String passengerWechat;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
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
        this.departure = departure == null ? null : departure.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo == null ? null : flightNo.trim();
    }

    public String getPassengerContact() {
        return passengerContact;
    }

    public void setPassengerContact(String passengerContact) {
        this.passengerContact = passengerContact == null ? null : passengerContact.trim();
    }

    public String getPassengerWechat() {
        return passengerWechat;
    }

    public void setPassengerWechat(String passengerWechat) {
        this.passengerWechat = passengerWechat == null ? null : passengerWechat.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderDetailInfo other = (OrderDetailInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getPassengerName() == null ? other.getPassengerName() == null : this.getPassengerName().equals(other.getPassengerName()))
            && (this.getPassengerTotal() == null ? other.getPassengerTotal() == null : this.getPassengerTotal().equals(other.getPassengerTotal()))
            && (this.getLuggageTotal() == null ? other.getLuggageTotal() == null : this.getLuggageTotal().equals(other.getLuggageTotal()))
            && (this.getDeparture() == null ? other.getDeparture() == null : this.getDeparture().equals(other.getDeparture()))
            && (this.getDestination() == null ? other.getDestination() == null : this.getDestination().equals(other.getDestination()))
            && (this.getFlightNo() == null ? other.getFlightNo() == null : this.getFlightNo().equals(other.getFlightNo()))
            && (this.getPassengerContact() == null ? other.getPassengerContact() == null : this.getPassengerContact().equals(other.getPassengerContact()))
            && (this.getPassengerWechat() == null ? other.getPassengerWechat() == null : this.getPassengerWechat().equals(other.getPassengerWechat()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getPassengerName() == null) ? 0 : getPassengerName().hashCode());
        result = prime * result + ((getPassengerTotal() == null) ? 0 : getPassengerTotal().hashCode());
        result = prime * result + ((getLuggageTotal() == null) ? 0 : getLuggageTotal().hashCode());
        result = prime * result + ((getDeparture() == null) ? 0 : getDeparture().hashCode());
        result = prime * result + ((getDestination() == null) ? 0 : getDestination().hashCode());
        result = prime * result + ((getFlightNo() == null) ? 0 : getFlightNo().hashCode());
        result = prime * result + ((getPassengerContact() == null) ? 0 : getPassengerContact().hashCode());
        result = prime * result + ((getPassengerWechat() == null) ? 0 : getPassengerWechat().hashCode());
        return result;
    }
}