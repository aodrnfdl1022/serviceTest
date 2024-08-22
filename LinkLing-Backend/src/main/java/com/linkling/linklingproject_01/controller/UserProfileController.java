package com.linkling.linklingproject_01.controller;

import com.linkling.linklingproject_01.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable String id) {
        return userProfileService.getUserProfile(id)
                .map(userData -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("user_profile", userData.getUserProfile());
                    response.put("user_id", userData.getUserId());
                    response.put("user_name", userData.getUserName());
                    response.put("user_gender", userData.getUserGender());
                    response.put("online", true);
                    response.put("follower", 0);
                    response.put("user_nation", userData.getUserNation());
                    response.put("user_info", userData.getUserInfo());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String id, @RequestBody Map<String, Object> profileData) {
        try {
            String userProfile = (String) profileData.get("user_profile");
            String userName = (String) profileData.get("user_name");
            String userInfo = (String) profileData.get("user_info");
            String userGender = (String) profileData.get("user_gender");
            String userNation = (String) profileData.get("user_nation");
            @SuppressWarnings("unchecked")
            List<String> userLang = (List<String>) profileData.get("user_lang");

            boolean updated = userProfileService.updateUserProfile(id, userProfile, userName, userInfo, userGender, userNation, userLang);

            if (updated) {
                return ResponseEntity.ok().body(Map.of("message", "Profile updated successfully"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to update profile: " + e.getMessage()));
        }
    }
}
