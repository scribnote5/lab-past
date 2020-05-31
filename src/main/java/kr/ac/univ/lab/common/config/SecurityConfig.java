package kr.ac.univ.lab.common.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.ac.univ.lab.member.exception.CustomAuthenticationFailureHandler;
import kr.ac.univ.lab.member.exception.CustomAuthenticationSuccessHandler;
import kr.ac.univ.lab.member.service.MemberService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/imgages/**", "/summernote/**");
    }

  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정 
                .antMatchers("/member/list").hasAuthority("root")
                .antMatchers("/notice-board/list").hasAuthority("root")
                .antMatchers("/h2-console/**").permitAll() // h2-console 접근 허용
                .antMatchers("/**").permitAll()
            .and()
                .csrf().ignoringAntMatchers("/console/**") // h2-console csrf 제외
            .and()
            	.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Arrays.asList("localhost")))) // he-console X-Frame-Options 제외
            	.frameOptions().sameOrigin()
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/member/login")
                // 사용자 정의  handler
                .successHandler(CustomAuthenticationSuccessHandler())
                .failureHandler(CustomAuthenticationFailureHandler())
                .defaultSuccessUrl("/member/index")
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/logout/result")
                .invalidateHttpSession(true)
            .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/member/permission-denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
    
    // 사용자 정의  handler bean 등록
    @Bean
    public AuthenticationSuccessHandler CustomAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
    
    @Bean
    public AuthenticationFailureHandler CustomAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}