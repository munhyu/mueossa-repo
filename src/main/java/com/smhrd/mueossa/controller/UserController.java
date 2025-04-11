package com.smhrd.mueossa.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // 회원가입 처리
  @PostMapping("/userInsert")
  public String userInsert(User user, Model model) {
    TbUser tbUser = new TbUser(user);
    tbUser.setType("U");
    Timestamp joinedAt = new Timestamp(System.currentTimeMillis());
    int result = userRepository.userJoin(user.getId(),
        user.getPw(), // 비밀번호는 Repository에서 SHA2로 암호화
        user.getEmail(),
        user.getNick(),
        user.getGender(),
        joinedAt,
        "U"); // 회원 타입 (일반 사용자));
    if (result > 0) {
      return "redirect:/goPreference"; // 회원가입 성공 시 리다이렉트
    } else {
      model.addAttribute("error", "회원가입에 실패했습니다.");
      return "home"; // 실패 시 홈 화면으로 이동
    }
  }

  // 로그인 처리
  @PostMapping("/userLogin")
  public String userLogin(User user, HttpSession session, Model model) {

    // 비밀번호
    System.out.println("로그인 시도: ID=" + user.getId() + ", PW=" + user.getPw());
    TbUser tbUser = userRepository.findByIdAndPw(user.getId(), user.getPw());
    if (tbUser != null) {
      System.out.println("로그인 성공");
      return "redirect:/goHome"; // 메인 페이지로 리다이렉트
    } else {
      System.out.println("로그인 실패");
      model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "home"; // 에러 메시지와 함께 main.html로 이동
    }
  }

}