package com.sh.wxapp.handler;

import com.sh.wxapp.exception.BusinessException;
import com.sh.wxapp.rop.JsonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-21 14:22
 **/
@ControllerAdvice
public class BusinessExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public JsonResponse buzExceptionHandler(BusinessException be){
        return JsonResponse.fail(be.getCode(),be.getMessage());
    }
}
