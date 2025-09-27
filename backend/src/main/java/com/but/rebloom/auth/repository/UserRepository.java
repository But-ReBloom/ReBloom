package com.but.rebloom.auth.repository;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 조회 함수
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserEmailAndProvider(String userEmail, Provider provider);

    // 유저 존재 확인 함수
    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String userEmail);

    // 유저 아이디 수정
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.userId = :newId WHERE user.userEmail = :userEmail")
    void updateUserId(@Param("userEmail") String email, @Param("newId") String userId);

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.userPassword = :newPassword WHERE user.userEmail = :userEmail")
    void updateUserPassword(@Param("userEmail") String email, @Param("newPassword") String newPassword);
}
