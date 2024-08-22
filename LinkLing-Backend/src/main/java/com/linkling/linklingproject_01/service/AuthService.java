package com.linkling.linklingproject_01.service;

import com.linkling.linklingproject_01.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDAO userDAO;

    public boolean verifyId(String userId) {
        return !userDAO.existsByUserId(userId);
    }
}
