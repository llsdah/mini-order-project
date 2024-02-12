package com.fastcampus.miniorderproject.model;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class OrderInfoModel {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "OrderModel") // 외래 키 설정
    private OrderModel order;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ProductModel") // 외래 키 설정
    private ProductModel product;

    @Setter
    private String status;

}
