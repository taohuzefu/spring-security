package com.example.demo.repository;

import com.example.demo.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
  Optional<Agent> findByUserName(String userName);
}
