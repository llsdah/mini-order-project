package com.fastcampus.miniorderproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter //기본 1개
    private int count = 1;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private String Status; // 판매 가능 여부 체크 0 가능 1 판매중단 2 재고소진 불가
}
