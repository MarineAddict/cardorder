package com.sh.wxapp.req;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-03-14 21:52
 **/
public class PageableRequest {
    private Integer pageSize=10;

    private Integer pageNum =1;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
