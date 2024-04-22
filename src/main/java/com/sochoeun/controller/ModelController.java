package com.sochoeun.controller;

import com.sochoeun.Mapper.ModelEntityMapper;
import com.sochoeun.dto.ModelDTO;
import com.sochoeun.entity.Model;
import com.sochoeun.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto){
        Model model = modelEntityMapper.toModel(dto);
        model = modelService.save(model);
        return ResponseEntity.ok(modelEntityMapper.toModelDto(model));
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(modelService.findAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long model_id,@RequestBody Model model){
        Model update = modelService.update(model_id, model);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        modelService.delete(id);
        return ResponseEntity.ok("Delete Successfully");
    }
}
