package com.project.callbus.common.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(); //SpringSecurity에서 제공하는 비밀번호 암호화 객체.
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
			.passwordEncoder(encoder())
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
						.antMatchers("/v2/api-docs", "/configuration/ui"
							, "/swagger-resources", "/configuration/security"
							, "/swagger-ui.html", "/webjars/**","/swagger/**")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/login", "/home", "/member", "/api/v1/board").permitAll()
			.antMatchers("/v3/api-docs/**").permitAll()
			.antMatchers("/swagger-resources/**").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.usernameParameter("accountId")
			.successForwardUrl("/")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/");
	}
}
