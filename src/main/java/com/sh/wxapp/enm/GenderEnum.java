package com.sh.wxapp.enm;

public enum GenderEnum implements ConstantEnum{
    MALE(1,"男"),
    FEMAILE(2,"女");

    private Integer code;

    private String value;

    GenderEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }


    public String getValue() {
        return value;
    }


    public static GenderEnum getByCode(Integer code){
        for(GenderEnum en:GenderEnum.values()){
            if(en.getCode().equals(code)){
                return en;
            }
        }
        return null;
    }
}
