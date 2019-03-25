package com.example.demo.hello;

import com.example.demo.entity.Agent;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnyUserDetailService implements UserDetailsService {

  @Autowired AgentService agentService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Agent agent = agentService.getAgent(username);
    List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(agent.getRoles());
    return new User(agent.getUserName(), agent.getPassWord(), simpleGrantedAuthorities);
  }

  private List<SimpleGrantedAuthority> createAuthorities(String roleStr) {
    String[] roles = roleStr.split(",");
    List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
    for (String role : roles) {
      simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
    }
    return simpleGrantedAuthorities;
  }
}
