package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.Repository.MemberRepository;
import com.smhrd.mueossa.entity.MemberEntity;
import com.smhrd.mueossa.model.Member;

@Controller
public class MemberController {

  @Autowired
  private MemberRepository memberRepository;

  // // 회원가입 페이지 이동
  // @GetMapping("/goJoin")
  // public String goJoin() {
  // return "main"; // main.html로 이동
  // }

  // // 로그인 페이지 이동
  // @GetMapping("/goLogin")
  // public String goLogin() {
  // return "main"; // main.html로 이동
  // }

  // 회원가입 처리
  @PostMapping("/memberInsert")
  public String memberInsert(Member member, Model model) {
    MemberEntity memberEntity = new MemberEntity(member);
    memberEntity.setRole("N");
    memberRepository.save(memberEntity);
    return "redirect:/goLogin"; // 회원가입 후 로그인 페이지로 리다이렉트
  }

  // 로그인 처리
  @PostMapping("/memberLogin")
  public String memberLogin(Member member) {

    //

    System.out.println("로그인 시도: ID=" + member.getId() + ", PW=" + member.getPw());
    MemberEntity memberEntity = memberRepository.findByIdAndPw(member.getId(), member.getPw());
    if (memberEntity != null) {
      System.out.println("로그인 성공");
      return "redirect:/goIndex"; // 메인 페이지로 리다이렉트
    } else {
      System.out.println("로그인 실패");
      model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "main"; // 에러 메시지와 함께 main.html로 이동
    }
  }
}