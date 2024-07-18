package com.ALBAMA.cart_service.authentication;

import com.ALBAMA.cart_service.cartservice.port.cart.exception.NotAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@AllArgsConstructor
public class JwtRoleInterceptor implements HandlerInterceptor {
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!JwtUtil.allowRequest(request, environment.getProperty("jwt.secret"))) {
            throw new NotAuthorizedException();
        }
        return true;
    }

}
