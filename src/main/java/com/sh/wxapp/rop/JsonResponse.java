package com.sh.wxapp.rop;

/**
 * 通用返回体
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-21 14:26
 **/
public class JsonResponse<T> {

    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private JsonResponse (String code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data=data;
    }

    public static <T> JsonResponse success(String msg,T data){
        return new JsonResponse<T>("200",msg,data);
    }

    public static <T> JsonResponse fail(String code,String msg){
        return new JsonResponse<T>(code,msg,null);
    }

}
