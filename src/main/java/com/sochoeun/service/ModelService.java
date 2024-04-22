package com.sochoeun.service;

import com.sochoeun.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ModelService {
    Model save(Model model);
    List<Model> findByBrandId(Integer brandId);
    Page<Model> findAllByBrandIdPageable(Integer brandId, Integer pageNo,Integer pageSize);

}
