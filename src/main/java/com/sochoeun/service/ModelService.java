package com.sochoeun.service;

import com.sochoeun.entity.Model;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    Page<Model> findAllByBrandIdPageable(Integer brandId, Integer pageNo, Integer pageSize);
    List<Model> findAll();
    Model findId(Long id);
    Model update(Long id,Model model);
    void delete(Long id);

}
