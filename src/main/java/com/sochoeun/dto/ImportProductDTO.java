package com.sochoeun.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ImportProductDTO {
    private Long productId;
    private Integer importUnit;
    private BigDecimal pricePerUnit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateImport;
}
