package com.sh.wxapp.enm;

import com.sh.wxapp.exception.BusinessException;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-27 18:58
 **/
public enum CarTypeEnum {

    ECONOMIC(1, "经济型"),

    CONFORTABLE(2, "舒适型"),

    SUV(3, "SUV"),

    COMMERCIAL(4, "商务车"),

    LUXURY(5, "豪华型"),

    SPORT(6, "跑车");


    private Integer code;

    private String value;

    private CarTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


    public static CarTypeEnum getByCode(Integer code){
        for(CarTypeEnum enm:CarTypeEnum.values()){
            if(enm.getCode().equals(code)){
                return enm;
            }
        }
        throw new BusinessException(BusinessExceptionCodeEnum.NO_RESULT_FOUND.getCode(),"找不到对应的车型");
    }
}
