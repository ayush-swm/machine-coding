package issueAssignmentStrategy;

import exception.NoFreeAgentException;
import model.Issue;

public interface IssueAssignmentStrategy {
    void assignIssue(Issue issue) throws NoFreeAgentException;
}
