package com.sh.wxapp.filter;

import com.sh.wxapp.dto.user.UserInfoDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-26 14:51
 **/
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        UserInfoDTO userInfoDTO = (UserInfoDTO) req.getSession().getAttribute("userInfo");
        if (userInfoDTO != null) {
            req.getSession().setAttribute("userInfo", userInfoDTO);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
