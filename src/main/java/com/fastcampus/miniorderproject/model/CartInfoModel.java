package com.fastcampus.miniorderproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class CartInfoModel {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "CartModel") // 외래 키 설정
    private CartModel cart;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ProductModel") // 외래 키 설정
    private ProductModel product;

}
