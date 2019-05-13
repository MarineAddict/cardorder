package com.sh.wxapp.dto;


import java.io.Serializable;

/**
 * 分页查询公共参数
 */
public class PageableQueryDTO implements Serializable {

    private static final long serialVersionUID = -3863915750780223537L;
    /**
     * 一页多少条
     */
    private Integer pageSize = 10;
    /**
     * 第几页
     */
    private Integer pageNum = 1;

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
