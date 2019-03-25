package com.example.demo.service;

import com.example.demo.entity.Agent;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
  @Autowired AgentRepository agentRepository;

  public Agent getAgent(Long id) {
    return agentRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("can not find agent by id"));
  }
}
