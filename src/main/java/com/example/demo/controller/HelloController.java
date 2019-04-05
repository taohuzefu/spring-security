package com.example.demo.controller;

import com.example.demo.entity.Agent;
import com.example.demo.service.AgentService;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HelloController {

  @Autowired AgentService agentService;

  @RequestMapping("/")
  public String home() {
    return "home";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @PostMapping(value = "/register")
  public String doRegister(HttpServletRequest request) {
    Map<String, String> response = getParamNames(request);
    Agent agent =
        Agent.builder()
            .userName(response.get("username"))
            .passWord(response.get("password"))
            .nickName(response.get("nickname"))
            .roles("USERS")
            .build();
    agentService.insert(agent);
    return "redirect:register?success";
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private Map<String, String> getParamNames(HttpServletRequest request) {
    Map map = new HashMap();
    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String paramName = (String) paramNames.nextElement();

      String[] paramValues = request.getParameterValues(paramName);
      if (paramValues.length == 1) {
        String paramValue = paramValues[0];
        if (paramValue.length() != 0) {
          map.put(paramName, paramValue);
        }
      }
    }
    return map;
  }
}
