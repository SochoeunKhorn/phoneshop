package com.sochoeun.controller;

import com.sochoeun.Mapper.ModelMapper;
import com.sochoeun.dto.ModelDTO;
import com.sochoeun.entity.Model;
import com.sochoeun.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto){
        Model model = modelMapper.toModel(dto);
        model = modelService.save(model);
        return ResponseEntity.ok(modelMapper.toModelDto(model));
    }
}
