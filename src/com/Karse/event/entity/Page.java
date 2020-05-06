package com.Karse.event.entity;
/**
 * 分页对象实体类
 */

import java.util.List;

public class Page<T> {

    /**
     * totalCount:总记录数
     * totalPage：总页数
     * list：数据
     * currentPage：当前页数
     * rows：每页显示记录数
     */

    private int totalCount;
    private int totalPage;
    private List<T> list;
    private int currentPage;
    private int rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
