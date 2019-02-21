package com.sh.wxapp.controller;

import com.sh.wxapp.rop.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-21 15:56
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    public JsonResponse ping(){
        return JsonResponse.success("pong",null);
    }

}
