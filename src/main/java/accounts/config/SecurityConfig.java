/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.config;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("salam");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from users where username=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configureasdfghjkjhgfd");

        http.authorizeRequests()
                .antMatchers("/transaction/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/account/index").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/account/edit").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/payment").permitAll()
                //  .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .and()
                .formLogin()
                .loginPage("/account/login")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/account/login?logout")
                .and()
                .csrf();
    }

}
