package repository.impl;

import model.Issue;
import model.IssueStatus;
import model.IssueType;
import repository.IssueRepositoryInterface;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IssueRepositoryImpl implements IssueRepositoryInterface {

    private static IssueRepositoryImpl instance;
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    private final static ConcurrentHashMap<Integer, Issue> issuesMap = new ConcurrentHashMap<>();

    public static IssueRepositoryInterface getInstance() {
        if (instance == null) {
            synchronized (IssueRepositoryImpl.class) {
                if (instance == null) {
                    instance = new IssueRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        Integer key = atomicInteger.getAndIncrement();
        Issue issue = new Issue(key, transactionId, issueType, subject, description, email, IssueStatus.CREATED);
        issuesMap.put(key, issue);
    }

    @Override
    public Issue getIssueByEmailId(String email) {
        for (Issue issue : issuesMap.values()) {
            if (issue.getEmail().equals(email)) {
                return issue;
            }
        }
        throw new IllegalArgumentException("Issue with emailId " + email + " not found");
    }

    @Override
    public Issue getIssueByIssueId(Integer issueId) {
        if (issuesMap.containsKey(issueId)) {
            return issuesMap.get(issueId);
        } else {
            throw new IllegalArgumentException("Issue with id " + issueId + " not found");
        }
    }
}
