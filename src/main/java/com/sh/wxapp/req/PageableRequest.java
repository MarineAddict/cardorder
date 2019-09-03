package com.sh.wxapp.req;

import lombok.Data;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-14 21:52
 **/
@Data
public class PageableRequest {
    private Integer pageSize=10;

    private Integer pageNum =1;

}
