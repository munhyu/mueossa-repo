package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.Repository.MemberRepository;
import com.smhrd.mueossa.entity.MemberEntity;
import com.smhrd.mueossa.model.Member;

@Controller
public class MemberController {

  @Autowired
  private MemberRepository memberRepository;

  @GetMapping("/goJoin")
  public String goJoin() {
    return "join";
  }

  @GetMapping("goLogin")
  public String goLogin() {
    return "login";
  }

  // 회원가입
  @PostMapping("/memberInsert")
  public String memberInsert(Member member) {
    System.out.println(member.toString());
    MemberEntity memberEntity = new MemberEntity(member);
    memberEntity.setM_role("N");
    // 회원가입 로직
    memberRepository.save(memberEntity);
    return "redirect:/goLogin"; // 회원가입 후 로그인 페이지로 리다이렉트
  }

  // // 로그인
  // @PostMapping("/memberLogin")
  // public String memberLogin(Member member) {
  // System.out.println(member.toString());
  // MemberEntity memberEntity =
  // memberRepository.findByM_idAndM_pw(member.getM_id(), member.getM_pw());
  // if (memberEntity != null) {
  // // 로그인 성공
  // System.out.println("로그인 성공");
  // return "redirect:/goIndex"; // 로그인 성공 후 메인 페이지로 리다이렉트
  // } else {
  // // 로그인 실패
  // return "redirect:/goLogin"; // 로그인 실패 시 다시 로그인 페이지로 리다이렉트
  // }
  // }

}
