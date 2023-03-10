package com.ohble.domain.refreshtoken.repository;

import com.ohble.domain.refreshtoken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(Long id);

    Optional<RefreshToken> findByPayload(String payload);
}
