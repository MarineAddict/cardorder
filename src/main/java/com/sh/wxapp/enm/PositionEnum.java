package com.sh.wxapp.enm;

import com.sh.wxapp.exception.BusinessException;

/*身份*/
public enum PositionEnum implements ConstantEnum{

    DRIVER(1,"司机"),
    MANAGER(2,"管理员"),
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
