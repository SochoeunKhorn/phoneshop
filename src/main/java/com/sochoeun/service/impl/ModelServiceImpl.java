package com.sochoeun.service.impl;

import com.sochoeun.entity.Model;
import com.sochoeun.repository.ModelRepository;
import com.sochoeun.service.ModelService;
import com.sochoeun.service.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Page<Model> findAllByBrandIdPageable(Integer brandId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageSize,pageNo);
        Page<Model> page = modelRepository.findAllByBrandId(brandId,pageable);
        return page;
    }

}
