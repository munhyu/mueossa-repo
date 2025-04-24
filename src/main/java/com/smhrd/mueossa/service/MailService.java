package com.smhrd.mueossa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbUser;

@Service
public class MailService {

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private UserRepository userRepository;

  /** email 값 받아 해당하는 유저정보 model에 추가 */
  public void findAccountToEmail(String email, Model model) {
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
      } catch (Exception e) {
        System.err.println("이메일 발송 실패: " + e.getMessage());
        e.printStackTrace();
        model.addAttribute("error", "이메일 발송 중 오류가 발생했습니다: " + e.getMessage());
      }
    } else {
      // 이메일이 존재하지 않는 경우
      model.addAttribute("error", "이메일이 존재하지 않습니다.");
    }
  }

  // Join 사용
  public String sendAuthCode(String email) {

    // 6자리 인증코드 생성
    int code = (int) ((Math.random() * 900000) + 100000);
    String authCode = String.valueOf(code);
    System.out.println("인증코드: " + authCode);

    // 이메일 전송
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setFrom("qkrxodud130@gmail.com"); // 발신자 이메일 설정 추가
    message.setSubject("무엇싸 회원가입 인증코드 안내");
    message.setText("인증코드: " + authCode);
    System.out.println("인증코드: " + authCode);
    try {
      mailSender.send(message);
      System.out.println(email + "로 인증코드 발송 완료");
      // 인증코드를 model에 담아서 html로 전달
      return authCode; // 인증코드 반환
      // user정보 모델에 추가
    } catch (Exception e) {
      System.err.println("이메일 발송 실패: " + e.getMessage());
      e.printStackTrace();
      return null; // 오류 발생 시 null 반환
    }

  }

}