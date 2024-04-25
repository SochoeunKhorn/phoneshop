package com.sochoeun.service.impl;

import com.sochoeun.Mapper.ProductMapper;
import com.sochoeun.dto.ImportProductDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.entity.ProductImportHistory;
import com.sochoeun.exception.ApiException;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.ImportProductHistoryRepository;
import com.sochoeun.repository.ProductRepository;
import com.sochoeun.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImportProductHistoryRepository importProductHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        product.setAvailableUnit(0);
        product.setSalePrice(BigDecimal.valueOf(0));
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductImportHistory> getProductImportHistory() {
        return importProductHistoryRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        String text = "Product with model id =%s and color id = %d was not found";
        return productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, text.formatted(modelId, colorId)));
    }

    @Override
    public void importProduct(ImportProductDTO importDTO) {
        // update on product
        Product product = getProduct(importDTO.getProductId());
        int availableUnit = 0;
        if (product.getAvailableUnit() != null) {
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        // create product history
        importDTO.setDateImport(importDTO.getDateImport());
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
        importProductHistoryRepository.save(productImportHistory);
    }

    @Override
    public Map<Integer, String> uploadProduct(MultipartFile file) {
        Map<Integer, String> map = new HashedMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("products");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Integer rowNumber = 0;
                try {
                    int cellIndex = 0;

                    Row row = rowIterator.next();
                    Cell cellNo = row.getCell(cellIndex++);

                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();

                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    int importUnit = (int) cellImportUnit.getNumericCellValue();

                    Cell cellImportPrice = row.getCell(cellIndex++);
                    Double importPrice = (Double) cellImportPrice.getNumericCellValue();


                    if (importUnit < 1) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0");
                    }

                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdAndColorId(modelId, colorId);
                    int availableUnit = 0;
                    if(product.getAvailableUnit() != null) {
                        availableUnit = product.getAvailableUnit();
                    }
                    product.setAvailableUnit(availableUnit + importUnit);
                    productRepository.save(product);

                    // save product import history
                    //ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setDateImport(importDate);
                    importHistory.setImportUnit(importUnit);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    importHistory.setProduct(product);
                    importProductHistoryRepository.save(importHistory);
                } catch (Exception e) {
                    map.put(rowNumber, e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {

        Product product = getProduct(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }
}
