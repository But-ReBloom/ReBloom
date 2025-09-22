package com.but.rebloom.repository;

import com.but.rebloom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 조회 함수
    Optional<User> findByUserEmail(String userEmail);

    // 유저 존재 확인 함수
    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
}
