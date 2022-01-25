package com.example.introduction.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.introduction.domain.Member;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String memberName) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", memberName)
            .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }

    @Override
    public void clearAll() { // 있으면 큰일나는 기능 아닐까?
    }
}
