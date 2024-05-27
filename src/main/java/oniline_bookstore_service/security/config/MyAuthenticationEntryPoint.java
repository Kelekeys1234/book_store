package oniline_bookstore_service.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

	 @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response,
	                         AuthenticationException authException) throws IOException {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print("{\"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\"}");
	        out.flush();
	    }

	}


