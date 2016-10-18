package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Member;

import java.util.List;

public interface MemberService {

    public void add(Member member);
    public void remove(String memberName);
    public List<Member> list();
}
