package com.sh.wxapp.req.order;

import com.sh.wxapp.req.PageableRequest;
import lombok.Data;

import java.util.Date;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-14 21:51
 **/
@Data
public class LiveOrderListQueryReq extends PageableRequest {
    private String orderNo;

    private Integer orderType;

    private Date createStartDate;

    private Date createEndDate;

}
