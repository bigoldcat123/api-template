package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SafeConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
			}
        };
    }
    
    @Bean
    public ProviderManager providerManager()throws Exception {
        AuthenticationProvider authenticationManager = new AuthenticationProvider() {
            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
			@Override
			public Authentication authenticate(
					Authentication authentication) throws AuthenticationException {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
			}
        };
        ProviderManager p = new ProviderManager(authenticationManager);
        return p;
    }
    /**
     * Disable cross-site request forgery (CSRF) protection.
     * <p>
     * This is needed because the API Gateway does not support CSRF protection.
     *
     * @param httpSecurity the {@link HttpSecurity} to configure
     * @return the {@link SecurityFilterChain}
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(CorsConfigurer::disable)
                .sessionManagement(SessionManagementConfigurer::disable)
                // Logout is not necessary because the API Gateway does not support login.
                .logout(LogoutConfigurer::disable)
                // Form login is not necessary because the API Gateway does not support login.
                .formLogin(FormLoginConfigurer::disable)
                // Disable CSRF protection because the API Gateway does not support it.
                .csrf(CsrfConfigurer::disable)
                // Allow unauthenticated access to the test API.
                .authorizeHttpRequests(config -> config.requestMatchers("/test/**").permitAll()
                        // All other API endpoints require authentication.
                        .anyRequest().authenticated());
        DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity.build();
        return defaultSecurityFilterChain;
    }
}
