package issueAssignmentStrategy;

import repository.IssueRepositoryInterface;

public class IssueAssignmentStrategyFactory {
    public static IssueAssignmentStrategy getAssignmentStrategy() {
        return new RandomIssueAssignmentStrategy();
    }
}
