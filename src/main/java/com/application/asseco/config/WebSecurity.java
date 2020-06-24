package com.application.asseco.config;

import com.application.asseco.config.security.AssecoLogoutHandler;
import com.application.asseco.config.security.JWTAuthenticationFilter;
import com.application.asseco.config.security.JWTAuthorizationFilter;
import com.application.asseco.config.security.JWTVerifier;
import com.application.asseco.service.ActiveJwtService;
import com.application.asseco.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.application.asseco.config.security.SecurityConstants.LOGIN_URL;
import static com.application.asseco.config.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final AssecoLogoutHandler logoutHandler;

    private final UserDetailsServiceImpl userDetailsService;

    private final JWTVerifier jwtVerifier;

    private final ActiveJwtService activeJwtService;


    public WebSecurity(AssecoLogoutHandler logoutHandler, UserDetailsServiceImpl userDetailsService, JWTVerifier jwtVerifier, ActiveJwtService activeJwtService) {
        this.logoutHandler = logoutHandler;
        this.userDetailsService = userDetailsService;
        this.jwtVerifier = jwtVerifier;
        this.activeJwtService = activeJwtService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers(LOGIN_URL).permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), activeJwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtVerifier, userDetailsService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setExposedHeaders(
                Arrays.asList("Authorization", "Cache-Control", "Content-Language", "Content-Type",
                        "Expires", "Last-Modified", "Pragma", "X-Total-Count"));

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
