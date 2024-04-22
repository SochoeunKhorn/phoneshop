package com.sochoeun.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ImportProductDTO {
    private Long productId;
    private Integer importUnit;
    private BigDecimal pricePerUnit;
    private LocalDateTime dateImport;
}
