package com.trainingapps.cropdeal.userMicroService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.trainingapps.cropdeal.userMicroService.Filters.JwtRequestFilter;
import com.trainingapps.cropdeal.userMicroService.service.UserService;

//import com.trainingapps.cropdeal.dealerMicroService.Filters.JwtRequestFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors()
        .and().csrf().disable().authorizeRequests()
					.antMatchers("/farmer/**").hasRole("FARMER")
					.antMatchers("/dealer/**").hasRole("DEALER")
					.antMatchers("/admin/**").permitAll()
//					.hasRole("ADMIN")
					.antMatchers("/users/**").permitAll()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/**").permitAll()
					.anyRequest().authenticated().and()
	                .exceptionHandling()
//	        		.and().formLogin();
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}
	
	@Bean
	public FilterRegistrationBean coresFilter() {
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin");
		corsConfiguration.addAllowedHeader("Content-Type");
		corsConfiguration.addAllowedHeader("Content-Disposition");
		corsConfiguration.addAllowedHeader("Accept");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", corsConfiguration);
		FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(source));
		return bean;
	}

}
