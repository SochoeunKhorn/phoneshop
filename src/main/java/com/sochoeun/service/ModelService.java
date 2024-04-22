package com.sochoeun.service;

import com.sochoeun.entity.Model;
import org.springframework.data.domain.Page;

public interface ModelService {
    Model save(Model model);
    Page<Model> findAllByBrandIdPageable(Integer brandId, Integer pageNo, Integer pageSize);

}
