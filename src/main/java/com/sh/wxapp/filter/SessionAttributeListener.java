package com.sh.wxapp.filter;

import com.sh.wxapp.dto.user.UserInfoDTO;
import com.sh.wxapp.util.SsoUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-27 10:39
 **/
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if("userInfo".equals(se.getName())){
            SsoUtil.set((UserInfoDTO) se.getValue());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if("userInfo".equals(se.getName())){
            SsoUtil.remove();
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        if("userInfo".equals(se.getName())){
            SsoUtil.set((UserInfoDTO) se.getValue());
        }
    }
}
