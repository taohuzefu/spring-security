package com.example.demo.service;

import com.example.demo.entity.Agent;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
  @Autowired AgentRepository agentRepository;

  public Agent getAgent(String userName) {
    return agentRepository
        .findByUserName(userName)
        .orElseThrow(() -> new RuntimeException("can not find agent by id"));
  }

  public void insert(Agent agent) {
    encryptPassword(agent);
    agentRepository.save(agent);
  }

  private void encryptPassword(Agent agent) {
    String password = agent.getPassWord();
    password = new BCryptPasswordEncoder().encode(password);
    agent.setPassWord(password);
  }
}
