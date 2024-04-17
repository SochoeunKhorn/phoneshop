package com.sochoeun.service.utils;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
    Integer DEFAULT_PAGE_LIMIT=2;
    Integer DEFAULT_PAGE_NUMBER=1;
    String PAGE_LIMIT="_limit";
    String PAGE_NUMBER="_page";

    static Pageable getPageable(int pageLimit, int pageNumber){
        if(pageLimit <0 ){
            pageLimit = DEFAULT_PAGE_LIMIT;
        }
        if(pageNumber < DEFAULT_PAGE_NUMBER){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        Pageable pageable = PageRequest.of(pageNumber-1,pageLimit);
        return pageable;
    }
}
