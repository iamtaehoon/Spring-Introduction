package com.example.introduction.repository;

import com.example.introduction.domain.Member;

public interface MemberRepositoryInterface {
    //등록
    Long save(Member member);

    //조회
    Member findById(Long memberId);
}
