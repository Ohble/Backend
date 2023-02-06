package com.ohble.domain.user.repository;

import com.ohble.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);
}
