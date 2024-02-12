package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.OrderInfoModel;
import com.fastcampus.miniorderproject.model.OrderModel;
import com.fastcampus.miniorderproject.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoModel,Long> {
    OrderInfoModel findByOrderAndProduct(OrderModel orderModel, ProductModel productModel);
}
