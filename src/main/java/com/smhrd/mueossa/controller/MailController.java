package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.Repository.EmailRepository;

@Controller
public class MailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    // 이메일 값을 가지고 사용자 정보를 조회하는 메소드
    @PostMapping("/findAccount")
    public String checkEmail(String email, Model model) {
        // 이메일 중복 체크
        if (emailRepository.findByEmail(email) != null) {
            // 6자리 인증코드 생성
            int code = (int) ((Math.random() * 900000) + 100000);
            String authCode = String.valueOf(code);
            System.out.println("인증코드: " + authCode);
            // 인증코드를 model에 담아서 html로 전달
            model.addAttribute("checkCode", authCode);
            System.out.println(email + "로 인증코드 발송");
            // 이메일 전송
            org.springframework.mail.SimpleMailMessage message = new org.springframework.mail.SimpleMailMessage();
            message.setTo(email);
            message.setSubject("무엇싸 인증코드 안내");
            message.setText("인증코드: " + authCode);
            try {
                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "findAccount";
        } else {
            // 이메일이 존재하지 않는 경우
            model.addAttribute("error", "이메일이 존재하지 않습니다.");
            return "findAccount";
        }
    }
}
