package com.example.introduction.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.introduction.domain.Member;
import com.example.introduction.repository.MemberRepositoryInterface;
import com.example.introduction.repository.MemoryMemberRepository;

class MemberServiceTest {
    MemberRepositoryInterface memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @AfterEach
    public void afterEach() {
        memberRepository.clearAll();
    }

    @Test
    void 회원가입() {
        Member member = new Member("member1");
        Long memberId = memberService.join(member);
        assertThat(memberService.findMember(memberId)).isEqualTo(Optional.of(member));
    }

    @Test
    void 중복된_이름으로_회원가입_예외() {
        Member member = new Member("member1");
        Long member1Id = memberService.join(member);
        assertThatThrownBy(() -> memberService.join(new Member("member1")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이미 존재하는 회원의 이름입니다.");
    }

    @Test
    void 전체_회원_조회() {
        Member member1 = new Member("member1");
        memberService.join(member1);
        Member member2 = new Member("member2");
        memberService.join(member2);
        Member member3 = new Member("member3");
        memberService.join(member3);
        Member member4 = new Member("member4");

        List<Member> members = memberService.findMembers();
        assertThat(members.contains(member1)).isTrue();
        assertThat(members.contains(member2)).isTrue();
        assertThat(members.contains(member3)).isTrue();
        assertThat(members.contains(member4)).isFalse();
    }

}