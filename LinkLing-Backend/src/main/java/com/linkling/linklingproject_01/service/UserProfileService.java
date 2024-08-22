package com.linkling.linklingproject_01.service;

import com.linkling.linklingproject_01.model.User;
import com.linkling.linklingproject_01.model.UserLanguage;
import com.linkling.linklingproject_01.repository.UserDAO;
import com.linkling.linklingproject_01.repository.UserLanguageDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserDAO userDAO;
    private final UserLanguageDAO userLanguageDAO;

    public Optional<User> getUserProfile(String userId) {
        return userDAO.findByUserId(userId);
    }

    @Transactional
    public boolean updateUserProfile(String userId, String userProfile, String userName, String userInfo, String userGender, String userNation, List<String> userLang) {
        Optional<User> userOptional = userDAO.findByUserId(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserProfile(userProfile);
            user.setUserName(userName);
            user.setUserInfo(userInfo);
            user.setUserGender(userGender);
            user.setUserNation(userNation);
            userDAO.save(user);

            // Update user languages
            UserLanguage userLanguage = userLanguageDAO.findByUserEmail(user.getUserEmail())
                    .orElse(new UserLanguage());
            userLanguage.setUserEmail(user.getUserEmail());
            userLanguage.setUserLang(String.join(",", userLang));
            userLanguageDAO.save(userLanguage);

            return true;
        }
        return false;
    }
}
