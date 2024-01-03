
package com.easybuy.app.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.easybuy.app.serviceimpl.UserDetailsServiceImpl;
import com.easybuy.app.serviceimpl.UsersServiceImpl;

@Component
public class JWTFilter extends OncePerRequestFilter {
	private static final String SECRET_KEY = "dfjhb356kbkbj236bkjbqyhdbqiudbsh";

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	// @Autowired
	// private UsersServiceImpl userService;
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String cookieString = null;
		if (authHeader == null) {
			cookieString = request.getHeader("Cookie");
		}
		// cookieString=userService.getDecryptedToken(cookieString);
		if (cookieString != null && cookieString.contains("token")) {
			// try {
			// System.out.println("Without encryption " + cookieString);
			// Cipher cipher = Cipher.getInstance("AES");
			// SecretKeySpec secretKey = new
			// SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
			// SecretKeySpec secretKey = new
			// SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
			// cipher.init(Cipher.DECRYPT_MODE, secretKey);

			// byte[] decodedBytes = Base64.getDecoder().decode(cookieString);
			// byte[] decryptedBytes = cipher.doFinal(decodedBytes);
			// String decryptedToken = new String(decryptedBytes);

			// cookieString=decryptedToken;
			// return decryptedToken;
			// } catch (Exception e) {
			// Handle decryption error
			// logger.error(e);
			// }
			System.out.println("This is the token after decryption " + cookieString);
			String[] parts = cookieString.split("; ");
			String token = null;

			for (String part : parts) {
				if (part.startsWith("token=")) {
					// Extract token part after 'token='
					token = part.substring(6);

					break; // Token found, exit the loop
				}
			}

			if (token != null) {
				authHeader = "Bearer " + token.trim();
				System.out.println("Extracted token: " + token);
			} else {
				System.out.println("Token not found in the cookie string");
			}

			if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
				String jwt = authHeader.substring(7);
				if (jwt == null || jwt.isBlank()) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
				} else {
					try {
						String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
						UserDetails userDetails = userDetailsService.loadUserByUsername(email);
						UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,
								userDetails.getPassword(), userDetails.getAuthorities());
						if (SecurityContextHolder.getContext().getAuthentication() == null) {
							SecurityContextHolder.getContext().setAuthentication(authToken);
						}
					} catch (JWTVerificationException exc) {
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
					}
				}

			}
		}
		filterChain.doFilter(request, response);

	}
}