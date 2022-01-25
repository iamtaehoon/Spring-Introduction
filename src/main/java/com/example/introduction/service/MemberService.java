package com.example.introduction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.introduction.domain.Member;
import com.example.introduction.repository.MemberRepositoryInterface;
import com.example.introduction.repository.MemoryMemberRepository;

@Service
public class MemberService {
    private final MemberRepositoryInterface memberRepository;

    @Autowired
    public MemberService(MemberRepositoryInterface memberRepository) {
        this.memberRepository = memberRepository;
    }

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
