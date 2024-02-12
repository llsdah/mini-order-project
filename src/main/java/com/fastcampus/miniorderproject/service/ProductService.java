package com.fastcampus.miniorderproject.service;

import com.fastcampus.miniorderproject.model.ProductModel;
import com.fastcampus.miniorderproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductModel saveProduct(ProductModel productModel) {
        ProductModel result = productRepository.save(productModel);
        return result;
    }


    public List<ProductModel> findProducts() {
        List<ProductModel> result = productRepository.findAll();
        return result;
    }
}
