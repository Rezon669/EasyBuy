/*
 * package com.easybuy.app.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.AuthenticationProvider; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter;
 * 
 * import com.easybuy.app.entity.Login; import
 * com.easybuy.app.repository.UsersRepo; import
 * com.easybuy.app.serviceimpl.UsersServiceImpl;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity //@EnableGlobalMethodSecurity public class SecurityConfig
 * {
 * 
 * @Autowired private UsersRepo usersRepo;
 * 
 * @Autowired private JWTFilter filter;
 * 
 * @Autowired private UserDetailsService uds;
 * 
 * 
 * @Autowired public void setFilter(JWTFilter filter) { this.filter = filter; }
 * 
 * public UserDetailsService userDetailsService() { // return new
 * UsersServiceImpl(); }
 * 
 * 
 * protected void configure(HttpSecurity http) throws Exception {
 * http.csrf().disable() .httpBasic().disable() .cors() .and()
 * .authorizeHttpRequests() .antMatchers("/easybuy/user/**").permitAll()
 * .antMatchers("/easybuy/product/**").hasRole("USER") .and()
 * .userDetailsService(uds) .exceptionHandling() .authenticationEntryPoint(
 * (request, response, authException) ->
 * response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized") )
 * .and() .sessionManagement()
 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 * 
 * http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); }
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception { return http.csrf().disable() .authorizeHttpRequests()
 * .antMatchers("/easybuy/user/**").permitAll() //
 * .requestMatchers("/easybuy/user/**").permitAll()
 * 
 * .and() .authorizeHttpRequests().antMatchers("/easybuy/product/**")
 * .authenticated().and() .sessionManagement()
 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) .and()
 * .authenticationProvider(authenticationProvider()) .addFilterBefore(filter,
 * UsernamePasswordAuthenticationFilter.class) .build(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public AuthenticationProvider authenticationProvider(){
 * DaoAuthenticationProvider authenticationProvider=new
 * DaoAuthenticationProvider();
 * authenticationProvider.setUserDetailsService(userDetailsService());
 * authenticationProvider.setPasswordEncoder(passwordEncoder()); return
 * authenticationProvider; }
 * 
 * @Bean public AuthenticationManager
 * authenticationManager(AuthenticationConfiguration config) throws Exception {
 * return config.getAuthenticationManager(); }
 * 
 * }
 */

package com.easybuy.app.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.easybuy.app.repository.UsersRepository;
import com.easybuy.app.security.JWTFilter;
import com.easybuy.app.serviceimpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private JWTFilter filter;
	@Autowired
	private UserDetailsServiceImpl uds;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().disable().cors().and()
		.authorizeHttpRequests()
		.antMatchers("/easybuy/user/**").permitAll()
		.antMatchers("/easybuy/product/secure/**").hasAuthority("ADMIN")
		.and().userDetailsService(uds)
		.exceptionHandling()
		.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}