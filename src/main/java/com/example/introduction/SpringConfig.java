package com.example.introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.introduction.repository.MemberRepository;
import com.example.introduction.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
