package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.User;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // 회원가입 처리
  @PostMapping("/userInsert")
  public String userInsert(User user, Model model) {
    TbUser tbUser = new TbUser(user);
    tbUser.setType("U");
    userRepository.save(tbUser);
    return "redirect:/goPreference"; // 회원가입 후 로그인 페이지로 리다이렉트
  }

  // 로그인 처리
  @PostMapping("/userLogin")
  public String userLogin(User user, Model model) {

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

  // // 아이디 중복 체크
  // @PostMapping("/checkId")
  // @ResponseBody
  // public boolean checkId(@RequestParam("id") String id) {
  // TbUser tbUser = userRepository.findByUserId(id);
  // if (tbUser != null) {
  // return false; // 중복된 아이디가 존재하는 경우
  // } else {
  // return true; // 중복되지 않은 아이디인 경우
  // }
  // }
}