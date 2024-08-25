package repository.impl;

import model.Agent;
import model.AgentIssueLog;
import model.AgentStatus;
import model.IssueType;
import repository.AgentRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AgentRepositoryImpl implements AgentRepositoryInterface {

  private static AgentRepositoryImpl instance;
  private AtomicInteger atomicInteger = new AtomicInteger(1);
  ;

  private ConcurrentHashMap<Integer, Agent> agentsMap = new ConcurrentHashMap<>();
  private ConcurrentHashMap<Integer, List<AgentIssueLog>> agentIssueLog = new ConcurrentHashMap<>();

  public static AgentRepositoryInterface getInstance() {
    if (instance == null) {
      synchronized (AgentRepositoryImpl.class) {
        if (instance == null) {
          instance = new AgentRepositoryImpl();
        }
      }
    }
    return instance;
  }

  @Override
  public void addAgent(String email, String name, List<IssueType> issueTypes) {
    Integer key = atomicInteger.getAndIncrement();
    Agent agent = new Agent(key, name, email, issueTypes, AgentStatus.ACTIVE);
    agentsMap.put(key, agent);
  }

  @Override
  public List<Agent> getAllAgent() {
    return new ArrayList<>(agentsMap.values());
  }

  @Override
  public Map<Integer, List<AgentIssueLog>> getAgentIssueLog() {
    return agentIssueLog;
  }

  @Override
  public void assignIssueToAgent(AgentIssueLog issueLog, Agent agent) {
    agentIssueLog.getOrDefault(agent.getAgentId(), new ArrayList<>()).add(issueLog);
  }

  @Override
  public List<AgentIssueLog> viewAgentsWorkHistory(Integer agentId) {
    return agentIssueLog.get(agentId);
  }
}
