package com.sh.wxapp.enm;

public enum GenderEnum {
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

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByCode(Integer code){
        for(GenderEnum en:GenderEnum.values()){
            if(en.getCode().equals(code)){
                return en.getValue();
            }
        }
        return null;
    }
}
