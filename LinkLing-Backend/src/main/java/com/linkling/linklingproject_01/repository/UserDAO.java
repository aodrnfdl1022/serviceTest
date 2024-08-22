package com.linkling.linklingproject_01.repository;

import com.linkling.linklingproject_01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);

    Optional<User> findByUserId(String userId);
}
