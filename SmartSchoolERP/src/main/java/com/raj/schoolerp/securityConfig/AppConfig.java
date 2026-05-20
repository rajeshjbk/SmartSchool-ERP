package com.raj.schoolerp.securityConfig;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http

		// Stateless Session
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

		// CORS
		.cors(cors -> {

			cors.configurationSource(new CorsConfigurationSource() {

				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

					CorsConfiguration cfg = new CorsConfiguration();

					cfg.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));

					cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

					cfg.setAllowCredentials(true);

					cfg.setAllowedHeaders(Collections.singletonList("*"));

					cfg.setExposedHeaders(Arrays.asList("Authorization"));

					return cfg;
				}
			});
		})

		// Authorization
		.authorizeHttpRequests(auth -> {

			auth

			// PUBLIC APIs
			.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

			.requestMatchers("/schoolerp/signIn", "/schoolerp/users/add").permitAll()

			// ================= ADMIN =================

			.requestMatchers(HttpMethod.POST, "/schoolerp/users/**", "/schoolerp/teachers/**",
					"/schoolerp/students/**", "/schoolerp/classes/**", "/schoolerp/subjects/**",
					"/schoolerp/timetable/**", "/schoolerp/books/**", "/schoolerp/book-issues/**",
					"/schoolerp/fee-structures/**", "/schoolerp/fee-transactions/**",
					"/schoolerp/exam-subjects/**", "/schoolerp/exams/**")
			.hasRole("ADMIN")

			.requestMatchers(HttpMethod.PUT, "/schoolerp/users/**", "/schoolerp/teachers/**",
					"/schoolerp/students/**", "/schoolerp/classes/**", "/schoolerp/subjects/**",
					"/schoolerp/timetable/**", "/schoolerp/books/**", "/schoolerp/book-issues/**",
					"/schoolerp/fee-structures/**", "/schoolerp/fee-transactions/**",
					"/schoolerp/exam-subjects/**", "/schoolerp/exams/**")
			.hasRole("ADMIN")

			.requestMatchers(HttpMethod.DELETE, "/schoolerp/users/**", "/schoolerp/teachers/**",
					"/schoolerp/students/**", "/schoolerp/classes/**", "/schoolerp/subjects/**",
					"/schoolerp/timetable/**", "/schoolerp/books/**", "/schoolerp/book-issues/**",
					"/schoolerp/fee-structures/**", "/schoolerp/fee-transactions/**",
					"/schoolerp/exam-subjects/**", "/schoolerp/exams/**", "/schoolerp/results/**",
					"/schoolerp/notices/**")
			.hasRole("ADMIN")

			// ================= TEACHER =================

			.requestMatchers(HttpMethod.POST,
			        "/schoolerp/attendance/**",
			        "/schoolerp/results/**",
			        "/schoolerp/notices/add")
			.hasAnyRole("ADMIN", "TEACHER")

			.requestMatchers(HttpMethod.PUT, "/schoolerp/attendance/**", "/schoolerp/notices/**","/schoolerp/results/**")
			.hasAnyRole("ADMIN", "TEACHER")

			// ================= STUDENT =================

			.requestMatchers(HttpMethod.POST,
			        "/schoolerp/leave-applications/apply")
			.hasAnyRole(
			        "ADMIN",
			        "TEACHER",
			        "STUDENT",
			        "PARENT"
			)
			.requestMatchers(HttpMethod.PUT,
			        "/schoolerp/leave-applications/**")
			.hasAnyRole(
			        "ADMIN",
			        "TEACHER",
			        "STUDENT",
			        "PARENT"
			)

			// ================= PARENT =================

			.requestMatchers(
			        "/schoolerp/students/parent/**"
			)
			.hasAnyRole(
			        "ADMIN",
			        "PARENT"
			)

			// ================= COMMON GET =================

			.requestMatchers(HttpMethod.GET,
			        "/schoolerp/students/**",
			        "/schoolerp/teachers/**",
			        "/schoolerp/classes/**",
			        "/schoolerp/subjects/**",
			        "/schoolerp/attendance/**",
			        "/schoolerp/results/**",
			        "/schoolerp/timetable/**",
			        "/schoolerp/books/**",
			        "/schoolerp/book-issues/**",
			        "/schoolerp/fee-transactions/**",
			        "/schoolerp/fee-structures/**",
			        "/schoolerp/notices/**",
			        "/schoolerp/exams/**",
			        "/schoolerp/exam-subjects/**",
			        "/schoolerp/leave-applications/**")
			.hasAnyRole(
			        "ADMIN",
			        "TEACHER",
			        "STUDENT",
			        "PARENT"
			)

			.anyRequest().authenticated();
		})

		// Disable CSRF
		.csrf(csrf -> csrf.disable())

		// JWT Filters
		.addFilterAfter(new JwtTokensGeneratorFilter(), BasicAuthenticationFilter.class)

		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)

		// HTTP BASIC LOGIN
		.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}