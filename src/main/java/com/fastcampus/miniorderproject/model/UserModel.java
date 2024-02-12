package com.fastcampus.miniorderproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;
    @Setter
    private String password;
    @Setter
    private String emailAddress;
    @Setter
    private String address;
    @Setter
    private String nickName;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private String status;




}
