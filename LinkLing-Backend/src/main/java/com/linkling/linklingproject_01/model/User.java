package com.linkling.linklingproject_01.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
public class User {
    @Id
    @Column(name = "user_email", nullable = false, length = 255)
    private String userEmail;

    @Column(name = "user_id", nullable = false, columnDefinition = "TEXT")
    private String userId;

    @Column(name = "user_pw", nullable = false, columnDefinition = "TEXT")
    private String userPw;

    @Column(name = "user_name", nullable = false, columnDefinition = "TEXT")
    private String userName;

    @Column(name = "user_gender", nullable = false, columnDefinition = "TEXT")
    private String userGender;

    @Column(name = "user_nation", nullable = false, columnDefinition = "TEXT")
    private String userNation;

    @Column(name = "user_info", columnDefinition = "TEXT")
    private String userInfo;

    @Column(name = "user_profile", columnDefinition = "TEXT")
    private String userProfile;
}
