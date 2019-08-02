package com.sh.wxapp.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-05-27 02:23
 **/
@RequestMapping("/main")
@Controller
public class MainPageController {


    @RequestMapping("/index")
    public String OrderInfoPage(){
        return "main";
    }

    @RequestMapping("/userManagePage")
    public String userManagePage(){
        return "user/user_manage";
    }

}
