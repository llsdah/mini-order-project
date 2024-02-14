package com.fastcampus.miniorderproject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Slf4j
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private MyJwtFilter myJwtFilter;
    @Autowired
    private MyJwtProvider myJwtProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(
                new MyJwtFilter(myJwtProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
        );
        http.addFilterBefore(myJwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    // 또는 별도의 CorsConfigurationSource 빈을 설정할 수도 있습니다.
    // 이 경우 해당 빈이 적용되며 http.cors()를 사용하지 않아도 됩니다.
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        // 모든 origin 허용 (실제 운영에서는 필요에 따라 수정)
        configuration.addAllowedHeader("*");

        // 다른 CORS 설정 추가 가능
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
