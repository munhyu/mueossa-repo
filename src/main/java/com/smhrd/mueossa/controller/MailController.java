package com.smhrd.mueossa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbUser;

@Controller
public class MailController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    // 이메일 값을 가지고 사용자 정보를 조회하는 메소드
    @PostMapping("/findAccount")
    public String checkEmail(String email, Model model) {
        // 이메일 중복 체크
        Optional<TbUser> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // 6자리 인증코드 생성
            int code = (int) ((Math.random() * 900000) + 100000);
            String authCode = String.valueOf(code);
            System.out.println("인증코드: " + authCode);

            // 이메일 전송
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("qkrxodud130@gmail.com"); // 발신자 이메일 설정 추가
            message.setSubject("무엇싸 인증코드 안내");
            message.setText("인증코드: " + authCode);

            try {
                mailSender.send(message);
                System.out.println(email + "로 인증코드 발송 완료");

                // 인증코드를 model에 담아서 html로 전달
                System.out.println("user : " + user);
                model.addAttribute("checkCode", authCode);
                // user정보 모델에 추가
                model.addAttribute("user", user.get());
                return "findAccount";
            } catch (Exception e) {
                System.err.println("이메일 발송 실패: " + e.getMessage());
                e.printStackTrace();
                model.addAttribute("error", "이메일 발송 중 오류가 발생했습니다: " + e.getMessage());
                return "findAccount";
            }
        } else {
            // 이메일이 존재하지 않는 경우
            model.addAttribute("error", "이메일이 존재하지 않습니다.");
            return "findAccount";
        }
    }
}
