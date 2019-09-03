package com.sh.wxapp.enm;

import com.sh.wxapp.exception.BusinessException;
import com.sun.tools.corba.se.idl.constExpr.Or;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-04 17:04
 **/
public enum OrderStatusEnum implements ConstantEnum{

    ISSUED(0, "未接单"),
    TAKEN(1, "已接单"),
    FINISHED(2, "已完成"),
    CANCELED(4, "已取消"),
    CHECKED_OUT(5,"已结账"),
    DELETED(6,"已删除")
    ;

    private Integer code;
    private String value;

    OrderStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


    public static OrderStatusEnum getByCode(Integer code){
        if(code==null){
            throw new NullPointerException(BusinessExceptionCodeEnum.PARAMETER_NULL.getMsg());
        }
        for(OrderStatusEnum enm: OrderStatusEnum.values()){
            if(enm.getCode().equals(code)){
                return enm;
            }
        }
        throw new BusinessException(BusinessExceptionCodeEnum.NO_RESULT_FOUND.getCode(),"找不到对应的订单类型");
    }
}
