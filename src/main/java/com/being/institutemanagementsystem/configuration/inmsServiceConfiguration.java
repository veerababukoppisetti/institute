package com.being.institutemanagementsystem.configuration;

import com.being.institutemanagementsystem.common.inmsProperties;
import com.being.institutemanagementsystem.filter.security.CustomAuthenticationFailureHandler;
import com.being.institutemanagementsystem.filter.security.CustomAuthenticationSuccessHandler;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Configuration
@Component
@SecurityScheme(name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class inmsServiceConfiguration {
    private final UserDetailsService userDetailsService;
    private static final String LOGOUT_ENDPOINT = "logout";

    public inmsServiceConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        // @formatter:off
        return http.csrf(AbstractHttpConfigurer::disable)
                   .formLogin(customizer -> customizer.loginProcessingUrl("/login")
                           .successHandler(new CustomAuthenticationSuccessHandler( new inmsProperties()))
                           .failureHandler(new CustomAuthenticationFailureHandler()))
                   .authorizeHttpRequests(authorize -> authorize
//                           .anyRequest()
//                               .permitAll()
                           .antMatchers( "/v3/api-docs/**",
                                   "/swagger-ui.html",
                                   "/swagger-ui/**",
                                   "/actuator/**")
                                .permitAll()
                           .antMatchers( HttpMethod.POST,"/institute-management-service/registration")
                                .authenticated())
                   .exceptionHandling(customizer -> customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                   .authenticationProvider(authenticationProvider())
                   .build();
        // @formatter:on
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userDetailsService);
        dap.setPasswordEncoder(passwordEncoder());

        return dap;
    }


    public WebSecurityCustomizer webSecurityCustomizer() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowBackSlash(true);
        return (web) -> web.httpFirewall(firewall);
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }


}