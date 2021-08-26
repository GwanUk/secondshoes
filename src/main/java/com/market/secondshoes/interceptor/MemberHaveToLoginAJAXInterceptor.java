package com.market.secondshoes.interceptor;

import com.market.secondshoes.ShoesConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class MemberHaveToLoginAJAXInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(ShoesConst.MEMBER_ID) == null) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("HaveToLogin");
            return false;
        }
        return true;
    }
}
