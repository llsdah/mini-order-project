package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long> {

}
