package com.but.rebloom.auth.repository;

import com.but.rebloom.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 조회 - userEmail
    Optional<User> findByUserEmail(String userEmail);

    // 유저 조회 - userId
    Optional<User> findByUserId(String userId);

    // 유저 존재 확인 - userEmail
    boolean existsByUserEmail(String userEmail);

    // 유저 존재 확인 - userId
    boolean existsByUserId(String userId);
}
