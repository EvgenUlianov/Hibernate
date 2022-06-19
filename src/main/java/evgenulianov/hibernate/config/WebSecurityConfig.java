package evgenulianov.hibernate.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig  {
    //WebSecurityConfigurerAdapter deprecated
    //https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    private static final String ROLE_USER = "USER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .and()
                .authorizeRequests().antMatchers("/persons/by-city").permitAll()
                .and()
                .authorizeRequests().antMatchers("/persons/save").hasRole(ROLE_USER)
                .and()
                .authorizeRequests().anyRequest().authenticated()//.hasRole(ROLE_USER)
                .and()
                .httpBasic()
                .and().csrf().disable()
        ;
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password")
                .roles(ROLE_USER)
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}