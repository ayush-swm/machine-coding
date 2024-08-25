package model;

import java.util.List;

public class Agent {
  private int agentId;
  private String agentName;
  private String agentEmail;
  private List<IssueType> issueTypeList;
  private AgentStatus agentStatus;

  public Agent(
      int agentId,
      String agentName,
      String agentEmail,
      List<IssueType> issueTypeList,
      AgentStatus agentStatus) {
    this.agentId = agentId;
    this.agentName = agentName;
    this.agentEmail = agentEmail;
    this.issueTypeList = issueTypeList;
    this.agentStatus = agentStatus;
  }

  public int getAgentId() {
    return agentId;
  }

  public String getAgentName() {
    return agentName;
  }

  public String getAgentEmail() {
    return agentEmail;
  }

  public List<IssueType> getIssueTypeList() {
    return issueTypeList;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }

  public void setAgentEmail(String agentEmail) {
    this.agentEmail = agentEmail;
  }

  public void setIssueTypeList(List<IssueType> issueTypeList) {
    this.issueTypeList = issueTypeList;
  }

  public AgentStatus getAgentStatus() {
    return agentStatus;
  }

  public void setAgentStatus(AgentStatus agentStatus) {
    this.agentStatus = agentStatus;
  }
}
