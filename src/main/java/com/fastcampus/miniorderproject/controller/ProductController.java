package com.fastcampus.miniorderproject.controller;

import com.fastcampus.miniorderproject.model.ProductModel;
import com.fastcampus.miniorderproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * @param
     * @return
     */
    @PostMapping
    @ResponseBody
    public ProductModel createProduct(@RequestBody ProductModel productModel) {
        log.info("[START] ProductController createProduct");
        ProductModel result = productService.saveProduct(productModel);
        log.info("[END] ProductController createProduct");
        return result;
    }

    @GetMapping
    @ResponseBody
    public List<ProductModel> findProducts(){
        log.info("[StART] ProductController findProducts");
        List<ProductModel> result = productService.findProducts();
        log.info("[END] ProductController findProducts");
        return result;
    }

}