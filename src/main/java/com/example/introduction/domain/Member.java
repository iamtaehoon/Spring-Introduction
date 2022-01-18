package com.example.introduction.domain;

public class Member {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}