package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.service.MailService;

@Controller
public class MailController {

    @Autowired
    private MailService mailSvc;

    // 이메일 값을 가지고 사용자 정보를 조회하는 메소드
    @PostMapping("/findAccount")
    public String checkEmail(String email, Model model) {
        mailSvc.findAccountToEmail(email, model);

        return "findAccount";
    }

}
