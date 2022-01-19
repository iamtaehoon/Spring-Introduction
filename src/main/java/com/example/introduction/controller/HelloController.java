package com.example.introduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.introduction.domain.Member;
import com.example.introduction.service.MemberService;

@Controller
public class HelloController {

    MemberService memberService = new MemberService();

    @GetMapping("/")
    public String home() {
        System.out.println("mainPage 실행");
        return "home";
    }

    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String saveMember(MemberForm memberForm) { //ModelAttribute 그거였나? 헷갈림..
        Member member = new Member(memberForm.getName());
        try {
            memberService.join(member);
        } catch (IllegalArgumentException e) {
            //오류메세지 넣어서 전달해야함.
            return "redirect:/members/new";
        }
        return "redirect:/";
    }

    // @GetMapping("/members")

}
