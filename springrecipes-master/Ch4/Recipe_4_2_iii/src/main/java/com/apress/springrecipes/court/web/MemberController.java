//FINAL 
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Member;
import com.apress.springrecipes.court.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/member/*")
public class MemberController {

    private MemberService memberService;

    // Wire service in constructor, available in application context
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/member/add")
    public String addMember(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("guests", memberService.list());
        return "memberList";
    }


    // Method mapped to URL /member/remove and /member/delete
    @RequestMapping(value = {"/member/remove", "/member/delete"}, method = RequestMethod.GET)
    public String removeMember(@RequestParam("memberName")
                               String memberName) {
        memberService.remove(memberName);

        // Use redirect so list is refreshed
        // Since the Controller uses a wildcard, any URL will do, use root ':'(i.e./member/)
        return "redirect:";
    }

    @RequestMapping(value = "processUser", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("member") Member member, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            memberService.add(member);
            return "redirect:";
        } else {
            return "";
        }
    }
}
