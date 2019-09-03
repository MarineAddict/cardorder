package com.sh.wxapp.enm;

import com.google.common.collect.Lists;

/**
 * 业务类型
 */
public enum BusinessTypeEnum implements ConstantEnum{

    PICK_UP(1,"接机"),
    DROP_OFF(2,"送机"),
    CAR_RENT(3,"租车");

    private Integer code;

    private String value;

    BusinessTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static BusinessTypeEnum valueOf(Integer code){
        return Lists.newArrayList(BusinessTypeEnum.values()).stream()
                .filter(businessTypeEnum -> businessTypeEnum.getCode().equals(code)).findFirst().orElse(null);
    }


    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
