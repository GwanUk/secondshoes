package com.market.secondshoes;

import com.market.secondshoes.argumentresolver.LoginMemberArgumentResolver;
import com.market.secondshoes.interceptor.LogInterceptor;
import com.market.secondshoes.interceptor.MemberHaveToLoginAJAXInterceptor;
import com.market.secondshoes.interceptor.MemberHaveToLoginInterceptor;
import com.market.secondshoes.interceptor.MemberLoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/js/**", "/error", "/imgs/**", "/assets/**");

        registry.addInterceptor(new MemberLoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/js/**", "/error", "/imgs/**", "/assets/**");

        registry.addInterceptor(new MemberHaveToLoginAJAXInterceptor())
                .order(3)
                .addPathPatterns("/wish/ajax/**", "/comment/ajax/**");

        registry.addInterceptor(new MemberHaveToLoginInterceptor())
                .order(4)
                .addPathPatterns("/member/edit/**", "/item/addForm", "/item/save", "/item/update/**", "/wish/**");


    }
}
