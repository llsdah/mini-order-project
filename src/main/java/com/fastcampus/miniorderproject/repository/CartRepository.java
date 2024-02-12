package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {
}
