package com.market.secondshoes.interceptor;

import com.market.secondshoes.ShoesConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString();

        request.setAttribute(ShoesConst.LOG_ID, logId);

        log.info("<== REQUEST [{}][{}][{}] ==>", logId, requestURI, handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("<== postHandle [{}] ==>", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String)request.getAttribute(ShoesConst.LOG_ID);

        log.info("<== RESPONSE [{}][{}][{}] ==>", logId, requestURI, handler);

        if (ex != null) {
            log.error("!!! afterCompletion error !!!", ex);
        }
    }
}
