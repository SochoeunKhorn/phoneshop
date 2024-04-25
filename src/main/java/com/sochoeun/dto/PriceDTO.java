package com.sochoeun.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDTO {
    @DecimalMin(value = "0.001",message = "Price must be greater than 0")
    private BigDecimal price;
}
