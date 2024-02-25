package com.fastcampus.miniorderproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

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
    private String email;
    @Setter
    private String address;
    @Setter
    private String nickName;
    @Setter
    private String authority;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private String status;


}
