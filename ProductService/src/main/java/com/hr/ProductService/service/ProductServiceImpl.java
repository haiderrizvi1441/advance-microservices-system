package com.hr.ProductService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.ProductService.entity.Product;
import com.hr.ProductService.exception.ProductServiceCustomException;
import com.hr.ProductService.model.ProductRequest;
import com.hr.ProductService.model.ProductResponse;
import com.hr.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product");

        Product product = Product.builder()
                        .productName(productRequest.getName())
                        .quantity(productRequest.getQuantity())
                        .price(productRequest.getPrice())
                        .build();
        
        productRepository.save(product);

        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Getting the product : {}",productId);
        //  Creating Product
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product with given id is not found", "PROUDCT_NOT_FOUND"));
        // Creating Product Response
        ProductResponse productResponse = new ProductResponse();
        // Copy PRODUCT -> PRODUCT RESPONSE
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;

        
    }
    
}
