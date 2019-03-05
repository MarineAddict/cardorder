package com.sh.wxapp.exception;

import com.sh.wxapp.enm.BusinessExceptionCodeEnum;

/**
 * 业务异常
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-21 14:08
 **/
public class BusinessException extends RuntimeException{
    private String code;
    /**
     * 业务异常信息
     */
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(BusinessExceptionCodeEnum enm){
        super(enm.getMsg());
        this.code=enm.getCode();
        this.message = enm.getMsg();
    }

    public BusinessException(String code,String message) {
        super(message);
        this.code=code;
        this.message = message;
    }public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
