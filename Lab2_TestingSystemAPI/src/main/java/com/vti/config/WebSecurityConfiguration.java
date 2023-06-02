package com.vti.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vti.backend.servicelayer.UserService;
import com.vti.config.exception.AuthExceptionHandler;

@SuppressWarnings("deprecation")
@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService service;

	@Autowired
	AuthExceptionHandler authExceptionHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		.exceptionHandling()
		.authenticationEntryPoint(authExceptionHandler)
		.accessDeniedHandler(authExceptionHandler)
		.and()
		.authorizeRequests()
		.antMatchers("/api/v1/users").hasAnyAuthority("Manager")
		.antMatchers("/api/v1/groups").hasAnyAuthority("Admin", "Manager")
		.antMatchers("/api/v1/groups/**").hasAnyAuthority("Admin", "Manager").antMatchers("/api/v1/login")
		.permitAll().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
	    configuration.applyPermitDefaultValues();
	    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
