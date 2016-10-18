package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MemberServiceImpl implements MemberService {

    private Map<String, Member> members = new TreeMap<String, Member>();

    public void add(Member member) {
        members.put(member.getName(), member);
    }

    public void remove(String memberName) {
        members.remove(memberName);
    }

    public List<Member> list() {
        return new ArrayList<Member>(members.values());
    }
}
