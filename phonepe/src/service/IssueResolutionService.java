package service;

import exception.InvalidInputException;
import exception.NoFreeAgentException;
import factory.AgentRepositoryFactory;
import factory.IssueRepositoryFactory;
import issueAssignmentStrategy.IssueAssignmentStrategy;
import issueAssignmentStrategy.IssueAssignmentStrategyFactory;
import model.Agent;
import model.AgentIssueLog;
import model.AgentStatus;
import model.FilterIssueRequest;
import model.Issue;
import model.IssueStatus;
import model.IssueType;
import repository.AgentRepositoryInterface;
import repository.IssueRepositoryInterface;

import java.util.Arrays;
import java.util.List;

public class IssueResolutionService {

  private final IssueRepositoryInterface issueRepositoryInterface;
  private final AgentRepositoryInterface agentRepositoryInterface;
  private final IssueAssignmentStrategy issueAssignmentStrategy;

  public IssueResolutionService() {
    this.issueRepositoryInterface = IssueRepositoryFactory.getIssueRepository();
    this.agentRepositoryInterface = AgentRepositoryFactory.getAgentRepository();
    this.issueAssignmentStrategy = IssueAssignmentStrategyFactory.getAssignmentStrategy();
  }

  public void createIssue(
      String transactionId, IssueType issueType, String subject, String description, String email) {
    try {
      issueRepositoryInterface.createIssue(transactionId, issueType, subject, description, email);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void addAgent(String email, String name, List<IssueType> issueTypes) {
    try {
      agentRepositoryInterface.addAgent(email, name, issueTypes);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void assignIssue(int issueId) {
    Issue issue = issueRepositoryInterface.getIssueByIssueId(issueId);
    if (issue == null) {
      System.out.println("Invalid Issue ID");
    }
    try {
      issueAssignmentStrategy.assignIssue(issue);
    } catch (NoFreeAgentException e) {
      System.out.println(e.getMessage());
    }
  }

  public Issue getIssue(FilterIssueRequest filterIssueRequest) {
    try {
      switch (filterIssueRequest.getFilterType()) {
        case ISSUE_ID:
          return getIssueByIssueId(filterIssueRequest.getFilterValue());
        case EMAIL_ID:
          return getIssueByEmailId(filterIssueRequest.getFilterValue());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  private Issue getIssueByEmailId(Object filterValue) throws InvalidInputException {
    String email = null;
    try {
      email = (String) filterValue;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new InvalidInputException(e.getMessage());
    }
    if (email != null) {
      return issueRepositoryInterface.getIssueByEmailId(email);
    }
    throw new InvalidInputException("email is null");
  }

  private Issue getIssueByIssueId(Object filterValue) throws InvalidInputException {
    Integer issueId = null;
    try {
      issueId = (Integer) filterValue;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new InvalidInputException(e.getMessage());
    }
    if (issueId != null) {
      return issueRepositoryInterface.getIssueByIssueId(issueId);
    }
    throw new InvalidInputException("issueId is null");
  }

  public void updateIssue(int issueId, IssueStatus issueStatus, String resolution) {
    Agent agent = null;
    try {
      agent = validateUpdateIssueInput(issueId, issueStatus);
    } catch (InvalidInputException e) {
      System.out.println(e.getMessage());
      return;
    }

    AgentIssueLog agentIssueLog =
        new AgentIssueLog(
            agent.getAgentId(), issueId, System.currentTimeMillis(), resolution, issueStatus);
    agentRepositoryInterface.assignIssueToAgent(agentIssueLog, agent);
  }

  public void resolveIssue(int issueId, String resolution) {
    Agent agent = null;
    try {
      agent = validateResolveIssueInput(issueId);
    } catch (InvalidInputException e) {
      System.out.println(e.getMessage());
      return;
    }

    AgentIssueLog agentIssueLog =
        new AgentIssueLog(
            agent.getAgentId(),
            issueId,
            System.currentTimeMillis(),
            resolution,
            IssueStatus.RESOLVED);
    agentRepositoryInterface.assignIssueToAgent(agentIssueLog, agent);
  }

  private Agent validateResolveIssueInput(int issueId) throws InvalidInputException {
    Issue issue = issueRepositoryInterface.getIssueByIssueId(issueId);
    if (issue == null) {
      throw new InvalidInputException("Invalid Issue ID");
    }
    if (issue.getIssueStatus().equals(IssueStatus.RESOLVED)) {
      throw new InvalidInputException("resolved issue can't be updated");
    }

    Agent agent = getAgentIdAssignedToIssue(issueId);
    if (agent != null) {
      throw new InvalidInputException("issue is not assined to any agent");
    }
    return agent;
  }

  private Agent validateUpdateIssueInput(int issueId, IssueStatus issueStatus)
      throws InvalidInputException {
    Issue issue = issueRepositoryInterface.getIssueByIssueId(issueId);
    if (issue == null) {
      throw new InvalidInputException("Invalid Issue ID");
    }
    if (issue.getIssueStatus().equals(IssueStatus.RESOLVED)) {
      throw new InvalidInputException("resolved issue can't be updated");
    }
    if (issueStatus.equals(IssueStatus.CREATED)) {
      throw new InvalidInputException("new  issue status can't be CREATED");
    }

    Agent agent = getAgentIdAssignedToIssue(issueId);
    if (agent != null) {
      throw new InvalidInputException("issue is not assined to any agent");
    }
    return agent;
  }

  private Agent getAgentIdAssignedToIssue(int issueId) {
    return new Agent(12, "name", "email", Arrays.asList(IssueType.FNO), AgentStatus.ACTIVE);
  }

  public List<AgentIssueLog> viewAgentsWorkHistory(Integer agentId) {
    return agentRepositoryInterface.viewAgentsWorkHistory(agentId);
  }
}
