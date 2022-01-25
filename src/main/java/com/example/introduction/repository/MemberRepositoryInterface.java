package com.example.introduction.repository;

import java.util.List;
import java.util.Optional;

import com.example.introduction.domain.Member;

public interface MemberRepositoryInterface {
    //등록
    Long save(Member member);

    //조회
    Optional<Member> findById(Long memberId);
    Optional<Member> findByName(String memberName);
    List<Member> findAll();

    void clearAll();
}
