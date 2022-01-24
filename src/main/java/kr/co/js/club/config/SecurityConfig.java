package kr.co.js.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("$2a$10$WyAM.w0AklrZc3W7dZ4DGeuV635LsUuY5JPrXG8VvLcpLUdpRUCRu")
                .roles("USER");
    }
 */


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");
        //권한이 없는 경유 로그인 페이지로 이동
        /*
        http.formLogin()//인가/인증에 문제시 로그인 화면
                .loginPage("/customlogin")
                .loginProcessingUrl("/login");
         */
        http.formLogin();
        //CSRF 토큰 비교하는 작업을 수행하지 않음
        http.csrf().disable();

    }




}
