package com.market.secondshoes.controller;

import com.market.secondshoes.ShoesConst;
import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberEditDto;
import com.market.secondshoes.dto.member.MemberInfoDto;
import com.market.secondshoes.dto.member.MemberLoginDto;
import com.market.secondshoes.dto.member.MemberSignUpDto;
import com.market.secondshoes.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signUp")
    public String signUpForm(Model model) {

        model.addAttribute("memberSignUpDto", new MemberSignUpDto());

        return "member/memberSignUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute MemberSignUpDto memberSignUpDto, BindingResult bindingResult) {

        if (!memberSignUpDto.getPassword().equals(memberSignUpDto.getPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck");
            bindingResult.rejectValue("passwordCheck", "passwordCheck");
        }

        if (bindingResult.hasErrors()) {
            return "member/memberSignUpForm";
        }

        memberService.join(memberSignUpDto.makeMember());

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("memberLoginDto", new MemberLoginDto());

        return "member/memberLoginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "member/memberLoginForm";
        }

        Member loginMember = memberService.login(memberLoginDto.getEmail(), memberLoginDto.getPassword()).orElse(null);

        if (loginMember == null) {
            bindingResult.reject("loginFail");
            return "member/memberLoginForm";
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
        model.addAttribute("memberInfoDto", MemberInfoDto.createMemberInfoDto(memberService.findMemberById(id)));
        return "member/memberInfoForm";
    }

    @GetMapping("/edit/{id}")
    public String memberUpdateForm(@PathVariable Long id, Model model, @Login Long loinId) {
        if (loinId != id) {
            return "redirect:/member/login";
        }
        model.addAttribute("memberEditDto", MemberEditDto.createMemberEditDto(memberService.findMemberById(id)));
        return "member/memberEditForm";
    }

    @PostMapping("/edit/{id}")
    public String memberUpdate(@PathVariable Long id, @ModelAttribute MemberEditDto memberEditDto, BindingResult bindingResult, @Login Long loinId, RedirectAttributes redirectAttributes) {
        if (loinId != id) {
            return "redirect:/member/login";
        }

        if (!memberEditDto.getPassword().equals(memberEditDto.getPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck");
            bindingResult.rejectValue("passwordCheck", "passwordCheck");
        }

        if (bindingResult.hasErrors()) {
            return "member/memberEditForm";
        }

        memberService.update(id, memberEditDto.makeMember());
        redirectAttributes.addAttribute("id", id);
        return "redirect:/member/find/{id}";
    }
}
