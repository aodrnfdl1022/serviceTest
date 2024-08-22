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
@Table(name = "user_lang")
public class UserLanguage {
    @Id
    @Column(name = "user_email", nullable = false, length = 255)
    private String userEmail;

    @Column(name = "user_lang", nullable = false, columnDefinition = "TEXT")
    private String userLang;
}
