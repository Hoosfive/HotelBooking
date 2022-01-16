package com.example.hotelbooking.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException {

        String referer = request.getHeader("Referer");
        referer = referer.substring(referer.lastIndexOf("/"));

        response.sendRedirect( String.format("/errorsNotificator?error=accessDenied&referer=%s", referer));
    }
}