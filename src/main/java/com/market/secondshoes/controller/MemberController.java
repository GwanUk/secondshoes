package com.market.secondshoes.controller;

import com.market.secondshoes.domain.Member;
import com.market.secondshoes.dto.MemberLoginDto;
import com.market.secondshoes.dto.MemberSignUpDto;
import com.market.secondshoes.exception.NotMemberException;
import com.market.secondshoes.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("memberSaveForm", new MemberSignUpDto());
        return "signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute MemberSignUpDto memberSignUpDto, BindingResult bindingResult) {

        if (!memberSignUpDto.getPassword().equals(memberSignUpDto.getPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck");
            bindingResult.rejectValue("passwordCheck", "passwordCheck");
        }

        if (bindingResult.hasErrors()) {
            log.info(" ! errors = {}", bindingResult);
            return "signUpForm";
        }
        Member member = Member.createMember(memberSignUpDto);
        memberService.join(member);
        return "redirect:/members/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberLoginDto", new MemberLoginDto());
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info(" !! {} {}", memberLoginDto.getEmail(), memberLoginDto.getPassword());
        Member member = memberService.findMemberByEmail(memberLoginDto.getEmail()).
                orElseThrow(() -> new NotMemberException("존재하지 않는 회원입니다."));

        if (!member.getPassword().equals(memberLoginDto.getPassword())) {
            throw new NotMemberException("존재하지 않는 회원입니다.");
        }

        redirectAttributes.addAttribute(member.getId());

        return "redirect:/{id}";
    }


}
