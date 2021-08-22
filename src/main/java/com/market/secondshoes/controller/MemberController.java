package com.market.secondshoes.controller;

import com.market.secondshoes.ShoesConst;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberLoginDto;
import com.market.secondshoes.dto.member.MemberSignUpDto;
import com.market.secondshoes.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signUp")
    public String signUpForm(Model model) {

        model.addAttribute("memberSignUpDto", new MemberSignUpDto());

        return "MemberSignUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute MemberSignUpDto memberSignUpDto, BindingResult bindingResult) {

        if (!memberSignUpDto.getPassword().equals(memberSignUpDto.getPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck");
            bindingResult.rejectValue("passwordCheck", "passwordCheck");
        }

        if (bindingResult.hasErrors()) {
            return "MemberSignUpForm";
        }

        memberService.join(memberSignUpDto.makeMember());

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("memberLoginDto", new MemberLoginDto());

        return "MemberLoginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "MemberLoginForm";
        }

        Member loginMember = memberService.login(memberLoginDto.getEmail(), memberLoginDto.getPassword()).orElse(null);

        if (loginMember == null) {
            bindingResult.reject("loginFail");
            return "MemberLoginForm";
        }

        HttpSession session = request.getSession();

        if (memberLoginDto.isMaintain()) {
            session.setMaxInactiveInterval(604800);
        }

        session.setAttribute(ShoesConst.MEMBER_ID, loginMember.getId());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {


        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }



    @GetMapping("/find/{id}")
    public String findMemberById(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.findMemberById(id));
        return "memberDetail";
    }
}
