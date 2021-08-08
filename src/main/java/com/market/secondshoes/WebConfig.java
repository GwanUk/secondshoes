package com.market.secondshoes;

import com.market.secondshoes.argumentresolver.LoginMemberArgumentResolver;
import com.market.secondshoes.interceptor.LogInterceptor;
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
                .excludePathPatterns("/css/**", "/*.ico", "/js/**", "/imgs/**", "/assets/**");

        registry.addInterceptor(new MemberLoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/item/sell/**");
    }
}
