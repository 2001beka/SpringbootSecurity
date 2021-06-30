package springboot.peaksoft.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springboot.peaksoft.configuration.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final LoginSuccessHandler loginSuccessHandler;

    private final UserDetailsService serviceSecurity;

    public SecurityConfig(LoginSuccessHandler loginSuccessHandler, UserDetailsService serviceSecurity) {
        this.loginSuccessHandler = loginSuccessHandler;
        this.serviceSecurity = serviceSecurity;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(("/admin/**")).access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/user").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler);
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceSecurity).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(serviceSecurity);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
