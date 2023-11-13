//package com.easybuy.app.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import com.easybuy.app.serviceimpl.LoginServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private LoginServiceImpl loginserviceimpl;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//
//				/*
//				 * security = security.cors().and().csrf().disable();
//				 * 
//				 * // Set session management to stateless security = security
//				 * .sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				 * .and();
//				 */
//
//				.authorizeRequests()
//				// .antMatchers("/easybuy/loginvalidation","/easybuy/signup","/easybuy/createaccount"
//				// ).permitAll()
//				// .antMatchers("/easybuy/secure/**").authenticated()
//				// .anyRequest().authenticated()
//				.and().formLogin().loginPage("/easybuy/signin").defaultSuccessUrl("/easybuy/welcome").permitAll().and()
//				.logout().logoutUrl("/easybuy/logout").invalidateHttpSession(true).clearAuthentication(true)
//				.logoutSuccessUrl("/easybuy/signin").permitAll();
//	}
//}
