package com.example.demo.jpa;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @EntityGraph(attributePaths = "authorities")
//    Optional<UserEntity> findOneWithAuthoritiesByUserId(String userId);
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);
    UserEntity findByUserId(String userId);
//    UserEntity findByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
