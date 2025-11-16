package com.incluipay.api.repository;

import com.incluipay.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encontra um usuario pelo seu email.
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica se um email existe.
     */
    boolean existsByEmail(String email);
}
