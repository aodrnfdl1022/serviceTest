package com.linkling.linklingproject_01.service;

import com.linkling.linklingproject_01.model.User;
import com.linkling.linklingproject_01.model.UserLanguage;
import com.linkling.linklingproject_01.repository.UserDAO;
import com.linkling.linklingproject_01.repository.UserLanguageDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserDAO userDAO;
    private final UserLanguageDAO userLanguageDAO;

    public Optional<UserProfileDTO> getUserProfile(String userId) {
        return userDAO.findByUserId(userId).map(user -> {
            UserLanguage userLanguage = userLanguageDAO.findByUserEmail(user.getUserEmail())
                    .orElse(new UserLanguage());
            List<String> languages = userLanguage.getUserLang() != null ?
                    Arrays.asList(userLanguage.getUserLang().split(",")) :
                    List.of();
            return new UserProfileDTO(user, languages);
        });
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

    public static class UserProfileDTO {
        private final User user;
        @Getter
        private final List<String> userLang;

        public UserProfileDTO(User user, List<String> userLang) {
            this.user = user;
            this.userLang = userLang;
        }

        public String getUserProfile() { return user.getUserProfile(); }
        public String getUserId() { return user.getUserId(); }
        public String getUserName() { return user.getUserName(); }
        public String getUserGender() { return user.getUserGender(); }
        public String getUserNation() { return user.getUserNation(); }
        public String getUserInfo() { return user.getUserInfo(); }
    }
}