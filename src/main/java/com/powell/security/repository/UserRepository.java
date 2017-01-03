package com.powell.security.repository;

import java.util.Optional;

import com.powell.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.userAuthorities r where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);
}