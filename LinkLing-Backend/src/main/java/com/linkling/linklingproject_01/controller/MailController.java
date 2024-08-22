package com.linkling.linklingproject_01.controller;



import com.linkling.linklingproject_01.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping()
public class MailController {

    private final MailService mailService;
    private int number; // 이메일 인증 숫자를 저장하는 변수

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    // 인증 이메일 전송
    @PostMapping("/check-email")
    public ResponseEntity<HashMap<String, Object>> sendMail(@RequestParam String mail) {
        System.out.println("요청옴!!!");
        HashMap<String, Object> response = new HashMap<>();

        try {
            number = mailService.sendMail(mail);  // 인증 번호 생성 및 메일 발송
            String num = String.valueOf(number);

            response.put("success", true);
            response.put("number", num);  // 이 값을 클라이언트에 보내는 건 보안상 권장되지 않지만, 디버깅이나 특정 요구사항이 있을 때 사용될 수 있습니다.
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    // 인증번호 일치여부 확인
    @GetMapping("/verify-email")
    public ResponseEntity<HashMap<String, Object>> verifyNumber(@RequestParam int token) {
        HashMap<String, Object> response = new HashMap<>();

        boolean isMatch = token == number;

        response.put("match", isMatch);

        if (isMatch) {
            response.put("message", "인증에 성공했습니다.");
        } else {
            response.put("message", "인증번호가 일치하지 않습니다.");
        }

        return ResponseEntity.ok(response);
    }
}
