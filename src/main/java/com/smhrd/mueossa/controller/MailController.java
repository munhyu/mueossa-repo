package com.smhrd.mueossa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // join에서 메일 보내기
    @PostMapping("/sendAuthCode")
    @ResponseBody
    public Map<String, Object> sendAuthCode(@RequestParam String email) {
        Map<String, Object> result = new HashMap<>();
        try {
            String checkCode = mailSvc.sendAuthCode(email); // 인증코드 생성 및 이메일 발송
            result.put("success", true);
            result.put("checkCode", checkCode); // 인증코드 반환
        } catch (Exception e) {
            result.put("success", false);
        }
        return result;
    }

}
