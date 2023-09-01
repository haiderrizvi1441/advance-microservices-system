package com.hr.ProductService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.ProductService.model.ProductRequest;
import com.hr.ProductService.model.ProductResponse;
import com.hr.ProductService.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    // Injecting Product  Service
    private ProductService productService;

    @PostMapping("/addproduct")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
       return new ResponseEntity<>(productId, HttpStatus.CREATED);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long productId){
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

    @GetMapping("/test")
    public String testing(){
        return "Product API is working fine.";
    }

}
