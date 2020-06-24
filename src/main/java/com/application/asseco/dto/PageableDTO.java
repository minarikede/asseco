package com.application.asseco.dto;


import java.io.Serializable;
import java.util.List;

public class PageableDTO implements Serializable {

    private int page;
    private int size = 20;
    private List<OrderDTO> sort;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PageableDTO page(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PageableDTO size(int size) {
        this.size = size;
        return this;
    }

    public List<OrderDTO> getSort() {
        return sort;
    }

    public void setSort(List<OrderDTO> sort) {
        this.sort = sort;
    }

    public PageableDTO sort(List<OrderDTO> sort) {
        this.sort = sort;
        return this;
    }
}
