package com.project.pma.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //auth.inMemoryAuthentication()
//        auth.jdbcAuthentication().dataSource(datasource)
//                .withDefaultSchema()
//                .withUser("miuser")
//                .password("pass")
//                .roles("ADMIN")
//                .and()
//                .withUser("Taz")
//                .password("tazz")
//                .roles("USER");

        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username,password, enabled"+
                        " from user_accounts where username = ?")
                .authoritiesByUsernameQuery("Select username, role "+
                        "from user_accounts where username= ?")
                .dataSource(datasource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http.authorizeRequests()
                //.antMatchers("/project/new").hasRole("ADMIN")
                //.antMatchers("/employee/new").hasAuthority("ADMIN") //---> Requires ROLE_ appended with ADMIN in role column ex: ROLE_ADMIN
                //.antMatchers("/project/save").hasRole("ADMIN")
                //.antMatchers("/employee/save").hasAuthority("ADMIN")//--->Does not require ROLE_, in table the role column must have ADMIN.
                //.antMatchers("/h2_console/**").permitAll()
                //.antMatchers("/","/**").permitAll() //---> example multiple patterns separated by commas
                //.antMatchers("/")
                //.authenticated()
                //.and()
                //.formLogin(); // --> default login page
                //.formLogin().loginPage("/login-page"); // ---> You can use your custom login page

        http.csrf().disable(); // ---> Cross-site Request Forgery by Spring
        //http.headers().frameOptions().disable();
    }
}
