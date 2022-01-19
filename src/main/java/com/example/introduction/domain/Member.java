package com.example.introduction.domain;

import java.util.Objects;

public class Member {
    private Long id;
    private String name;

    public Member() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Member(String name) {
        this.name = name;
    }

    public void putIdOnMember(Long memberId) {
        this.id = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Member member = (Member)o;
        return Objects.equals(getId(), member.getId()) && Objects.equals(getName(), member.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
