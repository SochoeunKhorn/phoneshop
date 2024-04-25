package com.sochoeun.service.impl;

import com.sochoeun.dto.ProductSaleDTO;
import com.sochoeun.dto.SaleDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.entity.Sale;
import com.sochoeun.entity.SaleDetail;
import com.sochoeun.exception.ApiException;
import com.sochoeun.repository.ProductRepository;
import com.sochoeun.repository.SaleDetailRepository;
import com.sochoeun.repository.SaleRepository;
import com.sochoeun.service.ProductService;
import com.sochoeun.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sellProduct(SaleDTO saleDTO) {
        // validation product
        List<Long> productId = saleDTO.getProducts().stream()
                .map(ProductSaleDTO::getProductId)
                .toList();
        // check  product has or not
        productId.forEach(productService::getProduct);

        // get product by id that we found
        List<Product> products = productRepository.findAllById(productId);

        // functional programing Map products easy using than array
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        // validate availableUnit
        saleDTO.getProducts()
                .forEach(ps -> {
                    Product product = productMap.get(ps.getProductId());
                    if (product.getAvailableUnit() < ps.getQty()){
                        throw new ApiException(HttpStatus.BAD_REQUEST,"Product [%s] is not enough in stock".formatted(product.getModel()));
                    }
                });

        // save to sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSoldDate());
        saleRepository.save(sale);

        // save to saleDetail
        saleDTO.getProducts().forEach(ps ->{
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getQty());
            saleDetailRepository.save(saleDetail);

            // cut stock availableUnit
            Integer availableUnit = product.getAvailableUnit() - ps.getQty();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });


     }
}
