package com.project.shop.auth;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	private final UserDetailsService userService;
	
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
				
				.requestMatchers("/static/**");
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests()
				.requestMatchers("/login", "/signup", "/user").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/test")
				.and()
				.logout()
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.and()
				.csrf().disable()
				.build();
	}
	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf((csrf) -> csrf.disable())
//            .cors((cors) -> cors.disable())
//            .authorizeHttpRequests(request -> request
//                .requestMatchers("/").permitAll()
//                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
//                .requestMatchers("/guest/**").permitAll()
//                .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//            );
//        
//        http.formLogin((formLogin) ->
//                        formLogin.permitAll()); // 기본 로그인 페이지
//        http.logout((logout) -> 
//                     logout.permitAll());		// 로그아웃 기본설정 (/logout으로 인증해제)
//        
//        return http.build();
//    }
	
//	protected SecurityFilterChain config(HttpSecurity http) throws Exception {
//	    http.csrf().disable();
//	    http.headers().frameOptions().disable();
//	    http.authorizeHttpRequests(authorize -> authorize
//	        .requestMatchers("/users/**").permitAll()
//	          .requestMatchers(PathRequest.toH2Console()).permitAll()
//	    );
//	    return http.build();
//	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticaltaionProvider() throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(userService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
