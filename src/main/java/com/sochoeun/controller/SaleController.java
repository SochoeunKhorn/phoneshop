package com.sochoeun.controller;

import com.sochoeun.dto.SaleDTO;
import com.sochoeun.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> sell(@RequestBody SaleDTO saleDTO){
        saleService.sellProduct(saleDTO);
        return ResponseEntity.ok("Success");
    }
}
