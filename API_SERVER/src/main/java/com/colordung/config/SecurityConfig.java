package com.colordung.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.colordung.auth.BaseSecurityHandler;
import com.colordung.auth.ajax.AjaxAuthenticationProvider;
import com.colordung.auth.ajax.filter.AjaxAuthenticationFilter;
import com.colordung.auth.jwt.JwtAuthenticationProvider;
import com.colordung.auth.jwt.filter.JwtAuthenticationFilter;
import com.colordung.auth.jwt.matcher.SkipPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationProvider jwtProvider;

//	@Autowired
//	private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AjaxAuthenticationProvider ajaxProvider;

//	@Autowired
//	private AjaxUserDetailsService ajaxUserDetailsService;

    @Autowired
    private BaseSecurityHandler securityHandler;

    @Autowired
    private ObjectMapper objectMapper;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

    private static final String LOGIN_ENTRY_POINT = "/login";
    private static final String TOKEN_ENTRY_POINT = "/token";
    private static final String ERROR_ENTRY_POINT = "/error";
    private static final String ROOT_ENTRY_POINT = "/api/**";
    private static final String SWAGGER_ENTRY_POINT1 = "/v2/api-docs";
    private static final String SWAGGER_ENTRY_POINT2 = "/configuration/ui";
    private static final String SWAGGER_ENTRY_POINT3 = "/swagger-resources/**";
    private static final String SWAGGER_ENTRY_POINT4 = "/configuration/security/**";
    private static final String SWAGGER_ENTRY_POINT5 = "/swagger-ui.html";
    private static final String SWAGGER_ENTRY_POINT6 = "/webjars/**";
    private static final String SWAGGER_ENTRY_POINT7 = "/swagger/**";
    private static final String NATIVE_ENTRY_POINT = "/api/native/**";
    private static final String DOWN_ENTRY_POINT = "/api/files/down/**";
    private static final String SAVEUSER_ENTRY_POINT = "/api/user/saveUser";
    private static final String DUPLUSER_ENTRY_POINT = "/api/user/duplicationUserIdCheck/**";


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/resources/**",
                NATIVE_ENTRY_POINT,
                DOWN_ENTRY_POINT,
                SWAGGER_ENTRY_POINT1,
                SWAGGER_ENTRY_POINT2,
                SWAGGER_ENTRY_POINT3,
                SWAGGER_ENTRY_POINT4,
                SWAGGER_ENTRY_POINT5,
                SWAGGER_ENTRY_POINT6,
                SWAGGER_ENTRY_POINT7,
                SAVEUSER_ENTRY_POINT
        )
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxProvider)
                .authenticationProvider(jwtProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), FilterSecurityInterceptor.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT1).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT1).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT2).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT3).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT4).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT5).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT6).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT7).permitAll()
                .antMatchers(SAVEUSER_ENTRY_POINT).permitAll()
                .antMatchers(DUPLUSER_ENTRY_POINT).permitAll()
                .antMatchers(NATIVE_ENTRY_POINT).permitAll()
                .antMatchers(DOWN_ENTRY_POINT).permitAll()
                .antMatchers(TOKEN_ENTRY_POINT).permitAll()
                .antMatchers(LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(ERROR_ENTRY_POINT).permitAll()
                .antMatchers(ROOT_ENTRY_POINT).authenticated()
                .and().cors().and()
        ;
    }

    @Bean
    public AntPathRequestMatcher antPathRequestMatcher() {
        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
    }

    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(securityHandler);
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }

    @Bean
    public SkipPathRequestMatcher skipPathRequestMatcher() {
        return new SkipPathRequestMatcher(Arrays.asList(
        		SAVEUSER_ENTRY_POINT,
        		DUPLUSER_ENTRY_POINT,
        		LOGIN_ENTRY_POINT,
                TOKEN_ENTRY_POINT,
                ERROR_ENTRY_POINT,
                NATIVE_ENTRY_POINT,
                DOWN_ENTRY_POINT,
                SWAGGER_ENTRY_POINT1,
                SWAGGER_ENTRY_POINT2,
                SWAGGER_ENTRY_POINT3,
                SWAGGER_ENTRY_POINT4,
                SWAGGER_ENTRY_POINT5,
                SWAGGER_ENTRY_POINT6,
                SWAGGER_ENTRY_POINT7
        )
        );
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addExposedHeader("jwt-header");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.OPTIONS);
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

