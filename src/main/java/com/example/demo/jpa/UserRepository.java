package com.example.demo.jpa;

import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByUserId(String userId);
    Iterable<UserEntity> findAll();
}
