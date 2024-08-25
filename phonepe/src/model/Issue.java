package model;

public class Issue {
  private int issueId;
  private String transactionId;
  private IssueType issueType;
  private String subject;
  private String description;
  private String email;
  private IssueStatus issueStatus;

  public Issue(
      int issueId,
      String transactionId,
      IssueType issueType,
      String subject,
      String description,
      String email,
      IssueStatus issueStatus) {
    this.issueId = issueId;
    this.transactionId = transactionId;
    this.issueType = issueType;
    this.subject = subject;
    this.description = description;
    this.email = email;
    this.issueStatus = issueStatus;
  }

  public int getIssueId() {
    return issueId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public IssueType getIssueType() {
    return issueType;
  }

  public String getSubject() {
    return subject;
  }

  public String getDescription() {
    return description;
  }

  public String getEmail() {
    return email;
  }

  public void setIssueId(int issueId) {
    this.issueId = issueId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public void setIssueType(IssueType issueType) {
    this.issueType = issueType;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public IssueStatus getIssueStatus() {
    return issueStatus;
  }

  public void setIssueStatus(IssueStatus issueStatus) {
    this.issueStatus = issueStatus;
  }
}
