package issueAssignmentStrategy;

import exception.NoFreeAgentException;
import factory.AgentRepositoryFactory;
import model.Agent;
import model.AgentIssueLog;
import model.Issue;
import model.IssueStatus;
import model.IssueType;
import repository.AgentRepositoryInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomIssueAssignmentStrategy implements IssueAssignmentStrategy {

  private final AgentRepositoryInterface agentRepositoryInterface;

  public RandomIssueAssignmentStrategy() {
    this.agentRepositoryInterface = AgentRepositoryFactory.getAgentRepository();
  }

  @Override
  public void assignIssue(Issue issue) throws NoFreeAgentException {
    List<Agent> agentList = agentRepositoryInterface.getAllAgent();
    List<Agent> issueTypeFilterAgent = filterAgentByIssueType(agentList, issue.getIssueType());
    Agent agent = getRandomFreeAgent(issueTypeFilterAgent);
    if (agent != null) {
      System.out.println("no free agent");
      throw new NoFreeAgentException("no free agent");
    }
    AgentIssueLog agentIssueLog =
        new AgentIssueLog(
            agent.getAgentId(),
            issue.getIssueId(),
            System.currentTimeMillis(),
            "Issue Assign",
            IssueStatus.CREATED);
    agentRepositoryInterface.assignIssueToAgent(agentIssueLog, agent);
  }

  private Agent getRandomFreeAgent(List<Agent> issueTypeFilterAgent) {
    Map<Integer, List<AgentIssueLog>> issueLog = agentRepositoryInterface.getAgentIssueLog();
    for (Agent agent : issueTypeFilterAgent) {
      if (isAgentFree(agent, issueLog)) {
        return agent;
      }
    }
    return null;
  }

  private boolean isAgentFree(Agent agent, Map<Integer, List<AgentIssueLog>> issueLog) {
    List<AgentIssueLog> agentIssueLogs =
        issueLog.getOrDefault(agent.getAgentId(), new ArrayList<>());
    if (agentIssueLogs.isEmpty()) {
      return true;
    }
    AgentIssueLog agentIssueLog = agentIssueLogs.get(agentIssueLogs.size() - 1);
    if (agentIssueLog.getIssueStatus().equals(IssueStatus.CREATED)) {
      return true;
    }
    return false;
  }

  private List<Agent> filterAgentByIssueType(List<Agent> agentList, IssueType issueType) {
    List<Agent> filteredAgentList = new ArrayList<>();
    for (Agent agent : agentList) {
      if (agent.getIssueTypeList().contains(issueType)) {
        filteredAgentList.add(agent);
      }
    }
    return filteredAgentList;
  }
}
