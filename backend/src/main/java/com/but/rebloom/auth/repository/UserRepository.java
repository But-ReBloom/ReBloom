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
    @Query("select u from User u where u.userEmail = :userEmail and u.userProvider = :userProvider")
    Optional<User> findByUserEmailAndProvider(
            @Param("userEmail") String userEmail,
            @Param("userProvider") Provider userProvider
    );
    Optional<User> findByUserId(String userId);

    // 유저 존재 확인 함수
    Boolean existsByUserId(String userId);
    Boolean existsByUserEmail(String userEmail);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM User u WHERE u.userEmail = :userEmail AND u.userId = :userId")
    Boolean existsByUserEmailAndUserId(
            @Param("userEmail") String userEmail,
            @Param("userId") String userId
    );

    // 유저 아이디 수정
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.userId = :newId WHERE user.userEmail = :userEmail")
    void updateUserIdByUserEmail(
            @Param("userEmail") String email,
            @Param("newId") String userId
    );

    // 유저 비밀번호 수정
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.userPassword = :newPassword WHERE user.userEmail = :userEmail")
    void updateUserPasswordByUserEmail(
            @Param("userEmail") String email,
            @Param("newPassword") String newPassword
    );
}
