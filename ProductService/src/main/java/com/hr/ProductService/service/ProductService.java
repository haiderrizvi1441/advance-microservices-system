package com.hr.ProductService.service;

import com.hr.ProductService.model.ProductRequest;
import com.hr.ProductService.model.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
    
}
