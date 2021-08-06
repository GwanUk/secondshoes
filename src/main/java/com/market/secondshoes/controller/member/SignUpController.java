package com.market.secondshoes.controller.member;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberSignUpDto;
import com.market.secondshoes.service.member.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String signUpForm(Model model) {

        model.addAttribute("memberSignUpDto", new MemberSignUpDto());

        return "signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute MemberSignUpDto memberSignUpDto, BindingResult bindingResult) {

        if (!memberSignUpDto.getPassword().equals(memberSignUpDto.getPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck");
            bindingResult.rejectValue("passwordCheck", "passwordCheck");
        }

        if (bindingResult.hasErrors()) {
            return "signUpForm";
        }

        Member member = Member.createMember(memberSignUpDto);
        signUpService.join(member);

        return "redirect:/members/login";
    }
}
