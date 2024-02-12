package com.fastcampus.miniorderproject.repository;

import com.fastcampus.miniorderproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
}
