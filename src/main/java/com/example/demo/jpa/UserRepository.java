package com.example.demo.jpa;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

//    @EntityGraph(attributePaths = "authorities")
//    Optional<UserEntity> findOneWithAuthoritiesByUserId(String userId);
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);
    UserEntity findByUserId(String userId);
//    Iterable<UserEntity> findAll();
}
