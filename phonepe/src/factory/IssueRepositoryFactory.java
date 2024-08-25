package factory;

import repository.IssueRepositoryInterface;
import repository.impl.IssueRepositoryImpl;

public class IssueRepositoryFactory {
    public static IssueRepositoryInterface getIssueRepository() {
        return IssueRepositoryImpl.getInstance();
    }
}
