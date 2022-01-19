package com.example.introduction.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.introduction.domain.Member;

public class MemoryMemberRepository implements MemberRepositoryInterface {
    private List<Member> memberRepository = new ArrayList<>();
    private Long sequence = -1L;
    @Override
    public Long save(Member member) {
        sequence += 1;
        member.putIdOnMember(sequence);
        memberRepository.add(member); // 동일 객체가 있는지 검증은 서비스단계가 맞음. 여기가 아님.
        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(memberRepository.get(memberId.intValue()));
    }

    @Override
    public Optional<Member> findByName(String memberName) {
        return memberRepository.stream().filter(member -> member.getName().equals(memberName)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return Collections.unmodifiableList(memberRepository);
    }
}
