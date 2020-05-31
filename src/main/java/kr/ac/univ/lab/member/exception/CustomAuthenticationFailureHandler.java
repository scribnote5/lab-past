package kr.ac.univ.lab.member.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errormsg = null;
        
        if(exception instanceof InternalAuthenticationServiceException) {
            errormsg = "존재하지 않는 이메일 입니다.";
        } else if(exception instanceof BadCredentialsException) {
            errormsg = "비밀번호가 일치하지 않습니다.";
        } else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            errormsg = "로그인 인증이 거부 되었습니다.";
        } 
        // 추후 잠금 및 비활성화 기능 구현 
        else if(exception instanceof LockedException) {
            errormsg = "잠금 계정입니다.";
        } else if(exception instanceof DisabledException) {
            errormsg = "비활성화된 계정입니다.";
        }  
        
        System.out.println("username: " + username);
		System.out.println("password: " + password);
		System.out.println("errormsg: " + errormsg);
        
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("errormsg", errormsg);
        
     
        request.getRequestDispatcher("/member/login/fail").forward(request, response);
    }

}