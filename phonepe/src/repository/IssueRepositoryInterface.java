package repository;

import model.Issue;
import model.IssueType;

public interface IssueRepositoryInterface {
    void createIssue(String transactionId, IssueType issueType, String subject, String description, String email);

    Issue getIssueByEmailId(String email);

    Issue getIssueByIssueId(Integer issueId);
}
