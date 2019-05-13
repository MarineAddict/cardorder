package com.sh.wxapp.dto;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**分页返回DTO
 * @param <T>
 */
public class PageableDTO<T>  implements Serializable {
    private static final long serialVersionUID = -6288024828857894076L;
    /**当前页*/
    private  int currentPage=1;
/**是否有下一页*/
    private  boolean hasNext;
 /**总共多少页*/
    private  int totalPage=0;

 /**数据*/
    private List<T> list;


    /**总记录数*/
    private long totalRecord;

    /**
     *  分页时 每页多少条
     */
    private int pageSize;
    /**
     *  当前页的数量 <= pageSize，该属性来自ArrayList的size属性
     */
    private int currentPageSize;

    public PageableDTO() {
    }


    public PageableDTO(PageInfo pageInfo) {
        this(pageInfo,null);
    }
    public PageableDTO(PageInfo pageInfo, Function<List,List<T>> function) {
            this.currentPage = pageInfo.getPageNum();
            this.totalPage = pageInfo.getPages();
            this.totalRecord = pageInfo.getTotal();
            this.pageSize = pageInfo.getPageSize();
            this.currentPageSize = pageInfo.getSize();
            this.hasNext = pageInfo.isHasNextPage();
        if(pageInfo.getList()!=null && pageInfo.getList().size()!=0 && function!=null){
                this.list = function.apply(pageInfo.getList());
            }else{
            this.list = pageInfo.getList();
        }
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(int currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
