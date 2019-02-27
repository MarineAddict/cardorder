package com.sh.wxapp.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-27 11:19
 **/
@Component
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean sessionFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        SessionFilter sessionFilter = new SessionFilter();
        registrationBean.setFilter(sessionFilter);
        registrationBean.setOrder(Integer.MIN_VALUE);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
