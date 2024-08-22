package com.linkling.linklingproject_01.repository;

import com.linkling.linklingproject_01.model.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLanguageDAO extends JpaRepository<UserLanguage, String> {
    Optional<UserLanguage> findByUserEmail(String userEmail);
}
