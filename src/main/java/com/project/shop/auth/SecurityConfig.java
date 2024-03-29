package com.project.shop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.shop.constant.RoleType;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // H2 콘솔 연결 deprecated 떠서 변경했는데 잘 되나요?
                .authorizeRequests(requests -> requests
                        .requestMatchers(
                                "/h2-console/**"    // H2 콘솔 허용
                        ).permitAll()
                        .requestMatchers("/notice/new/**","notice/modify/**", "notice/update/**", "notice/delete/**", "/admin/**").hasRole(RoleType.ADMIN.toString())
                        .requestMatchers("/my/**","/review**", "/review/**").hasAnyRole(RoleType.ADMIN.toString(), RoleType.USER.toString())
                		)
                .headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))

                .and()
                // 로그인
                .formLogin(login -> login
                        .loginPage("/members/login") // 로그인 페이지
                        .defaultSuccessUrl("/") // 성공 시 이동하는 페이지
                        .usernameParameter("email") // 이메일을 아이디로 사용
                        .failureUrl("/members/login/error")) // 실패페이지
                // 로그아웃
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 페이지
                        .logoutSuccessUrl("/")) // 로그아웃 성공 후 이동페이지
                .oauth2Login()
                .userInfoEndpoint()         //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.

    	
    	return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
    	return auth.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	return(web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    


}
