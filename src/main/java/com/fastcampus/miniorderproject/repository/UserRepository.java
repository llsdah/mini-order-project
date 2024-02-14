package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByEmailAndPassword(String email, String password);
}
