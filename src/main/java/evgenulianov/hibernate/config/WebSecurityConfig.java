package evgenulianov.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends GlobalMethodSecurityConfiguration {
    //WebSecurityConfigurerAdapter deprecated
    //https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    //https://www.baeldung.com/spring-security-method-security
    //

    private static final String PERSONS_READ = "PERSONS_READ";
    private static final String PERSONS_WRITE = "PERSONS_WRITE";
    private static final String PERSONS_DELETE = "PERSONS_DELETE";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // is needed for Postman testing: Authorisation Type = Basic Auth

        http
                .formLogin()
                .and()
                .httpBasic()
                .and().csrf().disable()
        ;
        return http.build();
    }

    @Autowired
    public void registerGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("reader").password("{noop}1").roles(PERSONS_READ)
                .and()
                .withUser("writer").password("{noop}1").roles(PERSONS_WRITE)
                .and()
                .withUser("deleter").password("{noop}1").roles(PERSONS_DELETE)
        ;
    }

}