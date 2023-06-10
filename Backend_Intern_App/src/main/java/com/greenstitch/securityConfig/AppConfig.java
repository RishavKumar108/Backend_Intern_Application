package com.greenstitch.securityConfig;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {
	

	@Bean
	 SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

//		http
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.csrf().disable()
//		.authorizeHttpRequests()
//		.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**","/greenstitch/customers/register","/greenstitch/customers/register-admin","/login").permitAll()
//		.requestMatchers( "/greenstitch/customers/update").hasRole("ADMIN")
//		.requestMatchers( "/greenstitch/customers/email/{email}","/greenstitch/customers/phone/{phone}","/greenstitch/customers/{id}").hasAnyRole("ADMIN","CUSTOMER")		
//		.anyRequest().authenticated().and()
//		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
//		.formLogin()
//		.and()
//		.httpBasic();
		
		
		
		http.securityMatchers((matchers) -> matchers
				.requestMatchers("*")
			)
		.sessionManagement((sessionManagement) ->
			sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		.csrf((csrf) -> csrf.disable())
		
		.authorizeHttpRequests((authorizeHttpRequests) ->
		authorizeHttpRequests
		.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**","/greenstitch/customers/register","/greenstitch/customers/register-admin","/login").permitAll()
		.requestMatchers( "/greenstitch/customers/update").hasRole("ADMIN")
		.requestMatchers( "/greenstitch/customers/email/{email}","/greenstitch/customers/phone/{phone}","/greenstitch/customers/{id}").hasAnyRole("ADMIN","CUSTOMER")
		.anyRequest().authenticated().and()
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
	).httpBasic();

		return http.build();
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	        .components(new Components()
	            .addSecuritySchemes("basicAuth", new SecurityScheme()
	                .type(SecurityScheme.Type.HTTP)
	                .scheme("basic"))
	            .addSecuritySchemes("bearer-jwt", new SecurityScheme()
	                .type(SecurityScheme.Type.HTTP)
	                .scheme("bearer")
	                .bearerFormat("JWT")
	                .in(SecurityScheme.In.HEADER)
	                .name("Authorization")))
	        .info(new Info()
	            .title("GreenStitch login signup RestApi")
	            .version("1.1")
	            .description("Spring boot Rest Application with Spring Security")
	            .termsOfService("8080/swagger-ui/index.html"))
	        .addSecurityItem(new SecurityRequirement()
	            .addList("basicAuth", Arrays.asList("read", "write"))
	            .addList("bearer-jwt", Arrays.asList("read", "write")));
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}