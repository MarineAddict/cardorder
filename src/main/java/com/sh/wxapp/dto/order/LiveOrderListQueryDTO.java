package com.sh.wxapp.dto.order;

import com.sh.wxapp.dto.PageableQueryDTO;
import lombok.Data;

import java.util.Date;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-05 16:49
 **/
@Data
public class LiveOrderListQueryDTO extends PageableQueryDTO {

    private String orderNo;

    private Integer orderType;

    private Date createStartDate;

    private Date createEndDate;


}
