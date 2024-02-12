package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,Long> {

}
