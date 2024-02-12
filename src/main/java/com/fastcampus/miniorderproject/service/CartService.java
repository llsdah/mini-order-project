package com.fastcampus.miniorderproject.service;

import com.fastcampus.miniorderproject.model.CartInfoModel;
import com.fastcampus.miniorderproject.model.CartModel;
import com.fastcampus.miniorderproject.model.ProductModel;
import com.fastcampus.miniorderproject.repository.CartInfoRepository;
import com.fastcampus.miniorderproject.repository.CartRepository;
import com.fastcampus.miniorderproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartInfoRepository cartInfoRepository;
    @Autowired
    private ProductRepository productRepository;


    public CartInfoModel saveCartInfo(ProductModel productModel) {

        Optional<CartModel> cartModel = cartRepository.findById(1L);
        Optional<ProductModel> getProductModel = productRepository.findById(productModel.getId());

        CartInfoModel cartInfoModel = new CartInfoModel();
        cartInfoModel.setCart(cartModel.get());
        cartInfoModel.setProduct(getProductModel.get());

        CartInfoModel result = cartInfoRepository.save(cartInfoModel);

        return result;
    }

    public CartModel findCart() {
        Optional<CartModel> result = cartRepository.findById(1L);
        return result.get();
    }

    public List<CartInfoModel> findCartInfo() {
        Optional<CartModel> cartModel = cartRepository.findById(1L);
        List<CartInfoModel> result = cartInfoRepository.findByCart(cartModel.get());
        return result;
    }

    public List<CartInfoModel> deleteCartInfo(List<ProductModel> list) {

        Optional<CartModel> cartModel = cartRepository.findById(1L);

        for(ProductModel productModel : list){
            Optional<ProductModel> getProductModel = productRepository.findById(productModel.getId());
            cartInfoRepository.deleteByCartAndProduct(cartModel.get(),getProductModel.get());
        }

        // 남은 장바구니 반환
        List<CartInfoModel> result = this.findCartInfo();

        return result;
    }
}
