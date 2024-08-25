package repository;

import model.Agent;
import model.AgentIssueLog;
import model.IssueType;

import java.util.List;
import java.util.Map;

public interface AgentRepositoryInterface {
    void addAgent(String email, String name, List<IssueType> issueTypes);

    List<Agent> getAllAgent();

    Map<Integer, List<AgentIssueLog>> getAgentIssueLog();

    void assignIssueToAgent(AgentIssueLog agentIssueLog, Agent agent);

    List<AgentIssueLog> viewAgentsWorkHistory(Integer agentId);
}
