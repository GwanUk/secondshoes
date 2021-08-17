package com.market.secondshoes.interceptor;

import com.market.secondshoes.ShoesConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session = request.getSession();

        if (session != null && session.getAttribute(ShoesConst.MEMBER_ID) != null) {
            request.setAttribute(ShoesConst.MEMBER_ID, session.getAttribute(ShoesConst.MEMBER_ID));
        }

        return true;
    }
}
