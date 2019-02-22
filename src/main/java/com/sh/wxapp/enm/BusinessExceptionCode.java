package com.sh.wxapp.enm;

/*业务异常*/
public enum BusinessExceptionCode {
    NORMAL("001","内部异常"),
    PARAMETER_NULL("101","请求参数为空"),
    NO_RESULT_FOUND("201","找不到数据"),



    TOKEN_EMPTY("301","无令牌"),
    TOKEN_ERROR("302","令牌验证失效");


    BusinessExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
