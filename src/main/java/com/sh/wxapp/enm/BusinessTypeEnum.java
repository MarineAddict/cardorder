package com.sh.wxapp.enm;

/**
 * 业务类型
 */
public enum BusinessTypeEnum {

    PICK_UP(1,"接机"),
    DROP_OFF(2,"送机"),
    CAR_RENT(3,"租车");

    private Integer code;

    private String value;

    BusinessTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
