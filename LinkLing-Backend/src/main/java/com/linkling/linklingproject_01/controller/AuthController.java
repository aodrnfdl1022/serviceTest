package com.linkling.linklingproject_01.controller;

import com.linkling.linklingproject_01.model.User;
import com.linkling.linklingproject_01.repository.UserDAO;
import com.linkling.linklingproject_01.repository.UserLanguageDAO;
import com.linkling.linklingproject_01.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/verify-id")
    public ResponseEntity<Boolean> verifyId(@RequestBody Map<String, String> requestBody) {
        String userId = requestBody.get("user_id");
        boolean exists = authService.verifyId(userId);
        System.out.println(userId + " / " + exists);

        return new ResponseEntity<>(exists, HttpStatus.OK);
    }


//    private final UserDAO userDAO;
//    private final UserLanguageDAO userLanguageDAO;
//
//    @PostMapping("/verify-id")
//    public ResponseEntity<Boolean> verifyId(@RequestBody Map<String, String> requestBody) {
//        String userId = requestBody.get("user_id");
//        boolean exists = !userDAO.existsByUserId(userId);
//        System.out.println(userId + " / " + exists);
//
//        return new ResponseEntity<>(exists, HttpStatus.OK);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody Map<String, String> requestBody) {
//        String userId = requestBody.get("user_id");
//        String userPw = requestBody.get("user_pw");
//        String userEmail = requestBody.get("user_email");
//        String userName = requestBody.get("user_name");
//        String userGender = requestBody.get("user_gender");
//        String userNation = requestBody.get("user_nation");
//
//        // 기본 검증
//        if (userId == null || userPw == null || userEmail == null || userName == null ||
//                userGender == null || userNation == null) {
//            return new ResponseEntity<>("입력값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
//        }
//
//        // 사용자 존재 여부 확인
//        if (userDAO.existsByUserId(userId)) {
//            return new ResponseEntity<>("사용자 ID가 이미 존재합니다.", HttpStatus.CONFLICT);
//        }
//
//        // 새로운 User 엔티티 생성
//        User newUser = User.builder()
//                .user_email(userEmail)
//                .user_id(userId)
//                .user_pw(userPw)  // 비밀번호 해싱은 실제로 구현해야 함
//                .user_name(userName)
//                .user_gender(userGender)
//                .user_nation(userNation)
//                .build();
//
//        // 데이터베이스에 새 사용자 저장
//        userDAO.save(newUser);
//
//        return new ResponseEntity<>("사용자가 성공적으로 등록되었습니다.", HttpStatus.OK);
//    }

}
