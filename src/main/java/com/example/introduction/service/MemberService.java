package com.example.introduction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.introduction.domain.Member;
import com.example.introduction.repository.MemberRepositoryInterface;
import com.example.introduction.repository.MemoryMemberRepository;

public class MemberService {
    private MemberRepositoryInterface memberRepository =new MemoryMemberRepository();

    public Long join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        if (memberRepository.findByName(member.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원의 이름입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
