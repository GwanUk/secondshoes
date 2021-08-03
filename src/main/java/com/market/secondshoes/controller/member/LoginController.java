package com.market.secondshoes.controller.member;

import com.market.secondshoes.Const;
import com.market.secondshoes.domain.Member;
import com.market.secondshoes.dto.member.MemberLoginDto;
import com.market.secondshoes.service.member.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("memberLoginDto", new MemberLoginDto());

        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "loginForm";
        }

        Member loginMember = loginService.login(memberLoginDto.getEmail(), memberLoginDto.getPassword()).orElse(null);

        if (loginMember == null) {
            bindingResult.reject("loginFail");
            return "loginForm";
        }

        HttpSession session = request.getSession();

        if (memberLoginDto.isMaintain()) {
            session.setMaxInactiveInterval(604800);
        }

        session.setAttribute(Const.LOGIN_MEMBER, loginMember);

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
}
