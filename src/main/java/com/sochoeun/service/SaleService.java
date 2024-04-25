package com.sochoeun.service;

import com.sochoeun.dto.SaleDTO;
import com.sochoeun.entity.Sale;
import com.sochoeun.entity.SaleDetail;

import java.util.List;

public interface SaleService {
    void sellProduct(SaleDTO saleDTO);
    Sale getSale(Long sale_id);
    void cancelSell(Long sale_id);
    List<Sale> getSales();
    List<SaleDetail> getSaleDetails();
}
