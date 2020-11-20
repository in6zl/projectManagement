package com.pactera.esc.core.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestBuilder {
    private static final int DEFAULT_PAGE_NUM = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private Integer pageNum;
    private Integer pageSize;
    private Sort sort = Sort.unsorted();

    public PageRequestBuilder pageNum(Integer pageNum) {
        if (null == pageNum || pageNum < 0) {
            this.pageNum = DEFAULT_PAGE_NUM;
        } else {
            this.pageNum = pageNum;
        }
        return this;
    }

    public PageRequestBuilder pageSize(Integer pageSize) {
        if (null == pageSize || pageSize < 1) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
        return this;
    }

    public PageRequestBuilder sort(Sort sort) {
        this.sort = null == sort ? Sort.unsorted() : sort;
        return this;
    }

    public PageRequestBuilder sort(Sort.Direction direction, String... properties) {
        if (null == properties || properties.length == 0) {
            this.sort = Sort.unsorted();
        }
        direction = null == direction ? Sort.Direction.DESC : direction;
        this.sort = Sort.by(direction, properties);
        return this;
    }


    public Pageable build() {
        return PageRequest.of(pageNum, pageSize, sort);
    }
}
