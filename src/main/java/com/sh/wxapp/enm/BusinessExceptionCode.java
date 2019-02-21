package com.sh.wxapp.enm;

/*业务异常*/
public enum BusinessExceptionCode {
    PARAMETER_NULL("001","请求参数为空"),
    NO_RESULT_FOUND("100","找不到数据");


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
