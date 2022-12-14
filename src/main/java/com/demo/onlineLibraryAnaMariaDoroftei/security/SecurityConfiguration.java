package com.demo.onlineLibraryAnaMariaDoroftei.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .cors().and().csrf().disable()
                .headers().frameOptions().disable()
        .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
        .and()
                .formLogin()
        .and()
                .authorizeRequests()
                .antMatchers("/h2-console").permitAll()
        .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
        .and()
                .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/layout").hasRole("READER")
//                .antMatchers("/layout").hasAnyRole("ADMIN", "LIBRARIAN")
//                .antMatchers("/").permitAll()
//                .and().formLogin()
//                .and().authorizeRequests()
//                .antMatchers("/h2-console/**")
//                .permitAll()
//        .and()
//                .formLogin()
//                .successForwardUrl("/layout")
//                .failureForwardUrl("/login/failure")
//        .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/layout")
//        .and()
//                .rememberMe().tokenValiditySeconds(2592000).key("secret!").rememberMeParameter("checkRememberMe")
//        .and()
//                .httpBasic()
//        .and()
//                .headers().frameOptions().disable();
//        return http.build();
//    }
