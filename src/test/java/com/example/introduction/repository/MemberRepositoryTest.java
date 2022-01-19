package com.example.introduction.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.introduction.domain.Member;

class MemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @Test
    void 회원_저장() {
        //given
        Member member1 = new Member("mem1");
        memberRepository.save(member1);
        //when
        Long member1Id = member1.getId();
        //then
        Assertions.assertThat(memberRepository.findById(member1Id)).isEqualTo(Optional.of(member1));
    }

    @Test
    void 회원_조회() {
        //given
        Member member1 = new Member("mem1");
        memberRepository.save(member1);
        Member member2 = new Member("mem2");
        memberRepository.save(member2);
        Member member3 = new Member("mem3");
        memberRepository.save(member3);

        //when
        Long member2Id = member2.getId();
        //then
        Assertions.assertThat(memberRepository.findById(member2Id)).isEqualTo(Optional.of(member2));
    }

    @Test
    void 회원_조회_다른_객체() {
        //given
        Member member1 = new Member("mem1");
        memberRepository.save(member1);
        Member member2 = new Member("mem2");
        memberRepository.save(member2);
        Member member3 = new Member("mem3");
        memberRepository.save(member3);
        //when
        Long member2Id = member2.getId();
        //then
        Assertions.assertThat(memberRepository.findById(member2Id)).isNotEqualTo(Optional.of(member1));
    }

    @Test
    void 회원_조회_이름으로() {
        //given
        Member member1 = new Member("mem1");
        memberRepository.save(member1);
        Member member2 = new Member("mem2");
        memberRepository.save(member2);
        Member member3 = new Member("mem3");
        memberRepository.save(member3);
        //when
        //then
        Assertions.assertThat(memberRepository.findByName("mem2")).isEqualTo(Optional.of(member2));
    }

    @Test
    void 회원_조회_없는_객체() {
        //given
        Member member1 = new Member("mem1");
        memberRepository.save(member1);
        Member member2 = new Member("mem2");
        memberRepository.save(member2);
        Member member3 = new Member("mem3");
        memberRepository.save(member3);
        //when
        //then
        Assertions.assertThat(memberRepository.findByName("mem100")).isEqualTo(Optional.empty());
    }

    @Test
    void 회원_전체_조회() {
        Member member1 = new Member("mem1");
        memberRepository.save(member1);
        Member member2 = new Member("mem2"); //저장x
        Member member3 = new Member("mem3");
        memberRepository.save(member3);

        List<Member> members = memberRepository.findAll();
        Assertions.assertThat(members.contains(member1)).isTrue();
        Assertions.assertThat(members.contains(member3)).isTrue();

        Assertions.assertThat(members.contains(member2)).isFalse();
    }

}