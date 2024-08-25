package model;

public class AgentIssueLog {
  private int agentId;
  private int issueId;
  private Long timeStamp;
  private String issueResolution;
  private IssueStatus issueStatus;

  public AgentIssueLog(
      int agentId, int issueId, Long timeStamp, String issueResolution, IssueStatus issueStatus) {
    this.agentId = agentId;
    this.issueId = issueId;
    this.timeStamp = timeStamp;
    this.issueResolution = issueResolution;
    this.issueStatus = issueStatus;
  }

  public int getAgentId() {
    return agentId;
  }

  public int getIssueId() {
    return issueId;
  }

  public Long getTimeStamp() {
    return timeStamp;
  }

  public String getIssueResolution() {
    return issueResolution;
  }

  public IssueStatus getIssueStatus() {
    return issueStatus;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }

  public void setIssueId(int issueId) {
    this.issueId = issueId;
  }

  public void setTimeStamp(Long timeStamp) {
    this.timeStamp = timeStamp;
  }

  public void setIssueResolution(String issueResolution) {
    this.issueResolution = issueResolution;
  }

  public void setIssueStatus(IssueStatus issueStatus) {
    this.issueStatus = issueStatus;
  }
}
