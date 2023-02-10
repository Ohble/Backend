package com.ohble.domain.user.useremailauth.repository;

import com.ohble.domain.user.user.User;
import com.ohble.domain.user.useremailauth.UserEmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserEmailAuthRepository extends JpaRepository<UserEmailAuth, Long> {
    Optional<UserEmailAuth> findByPayload(String payload);

    Optional<UserEmailAuth> findByUser(User user);

    void deleteByUser(User user);
}
