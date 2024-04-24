package com.csi.confign;

import com.csi.model.CustomEmployeeDetails;
import com.csi.service.CustomEmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class securityConfign extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomEmployeeDetailService customEmployeeDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")//starting with can be accessible to Both.
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("amol").password(this.passwordEncoder().encode("amol@123")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("komal").password(this.passwordEncoder().encode("komal@123")).roles("NORMAL");
        auth.userDetailsService(customEmployeeDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder(10);
    }
}
