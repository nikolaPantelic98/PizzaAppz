package com.app.pizzaorderingsystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    // a method that serves to redirect users to the home page based on the role
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String redirectUrl = request.getContextPath();
        if (userDetails.hasRole("Customer")) {
            redirectUrl += "/pos/customer_home";
        } else if (userDetails.hasRole("Admin")) {
            redirectUrl += "/pos/admin_home";
        }

        response.sendRedirect(redirectUrl);
    }
}
