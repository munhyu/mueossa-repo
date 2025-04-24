package com.smhrd.mueossa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mueossa.Repository.InquiryRepository;
import com.smhrd.mueossa.entity.TbInquiry;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.Inquiry;

import jakarta.servlet.http.HttpSession;

@Controller
public class InquiryController {

  @Autowired
  private InquiryRepository inquiryRepository;

  // 문의사항 등록 처리
  @PostMapping("/inquiryInsert")
  public String registerInquiry(Inquiry inquiryDetails) {
    // 문의사항 등록 로직
    TbInquiry inquiry = new TbInquiry(inquiryDetails);
    inquiryRepository.save(inquiry); // 문의사항 저장
    return "redirect:/goInquiryList"; // 성공 페이지로 리다이렉트
  }

  @GetMapping("/goInquiryList")
  public String goInquiryList(HttpSession session, Model model) {
    // id로 문의사항 조회
    TbUser user = (TbUser) session.getAttribute("user");
    // 세션에서 사용자 ID 가져오기
    if (user == null) {
      return "redirect:/goLogin"; // 로그인 페이지로 리다이렉트
    }
    // 사용자의 타입이 A면 관리자 페이지로 이동
    if (user.getType().equals("A")) {
      List<TbInquiry> inquiryList = inquiryRepository.findAll();
      model.addAttribute("inquiryList", inquiryList); // 모든 문의사항 조회
    } else {
      List<TbInquiry> inquiryList = inquiryRepository.qfindByUId(user.getId()); // 사용자 ID로 문의사항 조회
      model.addAttribute("inquiryList", inquiryList);
    }
    // 문의사항 목록 조회 로직
    return "inquiryList"; // inquiryList.html로 이동
  }

  // 문의 페이지 이동
  @PostMapping("/goInquiry")
  public String goInquiry(@RequestParam("idx") int idx, Model model) {
    TbInquiry inquiry = inquiryRepository.findById(idx).orElse(null); // 문의사항 조회
    model.addAttribute("inquiry", inquiry); // 모델에 문의사항 추가
    return "inquiry"; // inquiry.html로 이동
  }

  // 문의사항 삭제 처리
  @PostMapping("/deleteInquiry")
  @ResponseBody
  public String deleteInquiry(@RequestBody String idx) {
    int inquiryIdx = Integer.parseInt(idx.trim());
    inquiryRepository.deleteById(inquiryIdx); // 문의사항 삭제
    return "success"; // 성공 응답 반환
  }

}