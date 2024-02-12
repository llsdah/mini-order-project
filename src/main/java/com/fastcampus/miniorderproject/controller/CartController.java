package com.fastcampus.miniorderproject.controller;


import com.fastcampus.miniorderproject.model.CartInfoModel;
import com.fastcampus.miniorderproject.model.CartModel;
import com.fastcampus.miniorderproject.model.ProductModel;
import com.fastcampus.miniorderproject.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 회원 생성시 자동생성 데이터만 담으면 됩니다.
 */
@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     *
     * @param
     * @return
     */
    @PostMapping
    @ResponseBody
    public CartInfoModel createCart(@RequestBody  ProductModel productModel) {
        log.info("[START] CartController createCart");

        CartInfoModel result = cartService.saveCartInfo(productModel);
        log.info("[END] CartController createCart");
        return result;
    }

    // 장바구니 데이터 확인용
    @GetMapping
    @ResponseBody
    public List<CartInfoModel> findCartInfo() {
        log.info("[START] CartController findCartInfo");

        List<CartInfoModel> result = cartService.findCartInfo();
        log.info("[END] CartController findCartInfo");
        return result;
    }

    @DeleteMapping
    @ResponseBody
    public List<CartInfoModel> deleteCartInfo(@RequestBody  List<ProductModel> list) {
        log.info("[START] CartController deleteCartInfo");

        List<CartInfoModel> result = cartService.deleteCartInfo(list);
        log.info("[END] CartController deleteCartInfo");
        return result;
    }
}
