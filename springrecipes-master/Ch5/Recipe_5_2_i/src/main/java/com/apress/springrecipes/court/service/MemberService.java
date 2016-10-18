package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Member;

import java.util.Collection;

/**
 * Created by marten on 16-06-14.
 */
public interface MemberService {

    Collection<Member> findAll();
    Member find(long id);
}
