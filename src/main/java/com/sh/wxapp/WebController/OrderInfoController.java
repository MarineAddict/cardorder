package com.sh.wxapp.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-05-27 02:23
 **/
@RequestMapping("/orderInfoPage")
@Controller
public class OrderInfoController {


    @RequestMapping("/index")
    public String OrderInfoPage(){
        return "index";
    }

}
