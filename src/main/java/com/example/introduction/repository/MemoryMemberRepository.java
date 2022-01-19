package com.example.introduction.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.introduction.domain.Member;

public class MemoryMemberRepository implements MemberRepositoryInterface {
    private Map<Long, Member> memberRepository = new HashMap();
    private Long sequence = 0L;

    public MemoryMemberRepository() {
    }

    @Override
    public Long save(Member member) {
        sequence += 1;
        member.putIdOnMember(sequence);
        memberRepository.put(sequence, member); // 동일 객체가 있는지 검증은 서비스단계가 맞음. 여기가 아님.
        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(memberRepository.get(memberId));
    }

    @Override
    public Optional<Member> findByName(String memberName) {
        return memberRepository.values().stream().filter(member -> member.getName().equals(memberName)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberRepository.values());
    }
}
