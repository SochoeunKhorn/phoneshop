package com.sochoeun.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO {
    private List<?> list;
    private PaginationDTO pagination;

    public PageDTO(Page<?> page){
        this.list = page.getContent();
        this.pagination = PaginationDTO.builder()
                .pageSize(page.getSize())
                .pageNumber(page.getNumber()+1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
