package com.example.sport_api.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.sport_api.services.users.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyValidationFilter implements Filter {

    @Autowired
    UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String apiKey = request.getParameter("key");

        if (apiKey != null && userService.isValidApiKey(apiKey)) {
            chain.doFilter(request, response);

        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.getWriter().write("Unauthorized");
            httpServletResponse.setContentType("text/plain");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
