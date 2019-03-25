package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AnyUserDetailService anyUserDetailsService;

  @Autowired
  public WebSecurityConfig(AnyUserDetailService anyUserDetailsService) {
    this.anyUserDetailsService = anyUserDetailsService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/user/**")
        .hasRole("USER")
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/user")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(anyUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }

  /*    @Bean
  @Override
  public UserDetailsService userDetailsService() {
      UserDetails user =
          User.withDefaultPasswordEncoder()
              .username("user")
              .password("password")
              .roles("USER")
              .build();

      return new InMemoryUserDetailsManager(user);
  }*/

  // TODO PasswordEncoder
}
