package com.linkling.linklingproject_01.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String SENDER_EMAIL = "3575253@gmail.com";

    private static final ConcurrentHashMap<String, Integer> emailVerificationMap = new ConcurrentHashMap<>();
    private static final AtomicInteger numberGenerator = new AtomicInteger(100000);  // AtomicInteger for thread-safe number generation

    // 랜덤으로 숫자 생성
    private static int generateNumber() {
        return numberGenerator.addAndGet((int) (Math.random() * 90000));
    }

    public MimeMessage createMail(String recipientEmail, int number) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(SENDER_EMAIL);
            message.setRecipients(MimeMessage.RecipientType.TO, recipientEmail);
            message.setSubject("이메일 인증");
            String body = "<h3>요청하신 인증 번호입니다.</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>감사합니다.</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
            // Optionally log the exception or handle it in a different way
        }

        return message;
    }

    public int sendMail(String recipientEmail) {
        int number = generateNumber();
        MimeMessage message = createMail(recipientEmail, number);
        try {
            javaMailSender.send(message);
            emailVerificationMap.put(recipientEmail, number);
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally log the exception or handle it in a different way
        }
        return number;
    }

    public boolean verifyEmail(String recipientEmail, int number) {
        Integer storedNumber = emailVerificationMap.get(recipientEmail);
        return storedNumber != null && storedNumber.equals(number);
    }
}
