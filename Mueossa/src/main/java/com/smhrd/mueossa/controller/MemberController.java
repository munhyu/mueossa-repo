package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.smhrd.mueossa.Repository.MemberRepository;

@Controller
public class MemberController {

	@Autowired
	private MemberRepository repository;

	
	
}
