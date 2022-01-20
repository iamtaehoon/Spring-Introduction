package com.example.introduction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createMemberForm(Model model) {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String saveMember(MemberForm memberForm, Model model) { //ModelAttribute 그거였나? 헷갈림..
        Member member = new Member(memberForm.getName());
        try {
            memberService.join(member);
        } catch (IllegalArgumentException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("memberName", "회원 이름이 중복되었습니다.");
            model.addAttribute("errors", errors);
            return "members/createMemberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/members")
    public String showMemberList(Model model) { // 아 모델 여기에 넣는구나
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
