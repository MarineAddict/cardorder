package com.sh.wxapp.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-05-26 12:04
 **/
@Controller
@RequestMapping("/loginPage")
public class LoginPageController {

    @RequestMapping("/index")
    public String loginPageWeb(){
        return "index";
    }

}
