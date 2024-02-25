package com.fastcampus.miniorderproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class CartModel {
    @Id @GeneratedValue
    private Long id;

    @Setter
    @OneToOne
    @JoinColumn(name = "userModel") // 외래 키 설정
    private UserModel user;
}
