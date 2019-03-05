package com.sh.wxapp.enm;

import com.sh.wxapp.exception.BusinessException;

/*身份*/
public enum PositionEnum {

    TAKER(1,"接单人"),
    ISSUER(2,"发单人"),
    VISITOR(3,"游客");

    private Integer code;
    private String value;

    PositionEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static PositionEnum getPositionByCode(Integer code){
        if(code==null){
            throw new NullPointerException();
        }
        for(PositionEnum position: PositionEnum.values()){
            if(position.getCode().equals(code)){
                return position;
            }
        }
        throw new BusinessException();
    }
}
