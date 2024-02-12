package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.CartInfoModel;
import com.fastcampus.miniorderproject.model.CartModel;
import com.fastcampus.miniorderproject.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface CartInfoRepository extends JpaRepository<CartInfoModel,Long> {
    List<CartInfoModel> findByCart(CartModel cartModel);
    void deleteByCartAndProduct(CartModel cartModel, ProductModel productModel);
}
