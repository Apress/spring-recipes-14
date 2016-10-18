package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Members;
import com.apress.springrecipes.court.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marten on 16-06-14.
 */
@RestController
public class RestMemberController {

    private final MemberService memberService;

    @Autowired
    public RestMemberController(MemberService memberService) {
        super();
        this.memberService=memberService;
    }

    @RequestMapping("/members")
    public Members getRestMembers() {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }
}
