package com.sochoeun.controller;

import com.sochoeun.dto.SaleDTO;
import com.sochoeun.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancerSell(@PathVariable("id") Long id){
        saleService.cancelSell(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }
    @GetMapping("/details")
    public ResponseEntity<?> getSaleDetails(){
        return ResponseEntity.ok(saleService.getSaleDetails());
    }
}
