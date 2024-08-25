import model.FilterIssueRequest;
import model.FilterType;
import model.Issue;
import model.IssueStatus;
import model.IssueType;
import service.IssueResolutionService;

import java.util.Arrays;

public class Driver {
  public static void main(String[] args) {
    IssueResolutionService issueResolutionService = new IssueResolutionService();
    issueResolutionService.createIssue(
        "TR123",
        IssueType.FNO,
        "Fno order didn't triggered",
        "fno portal not working",
        "ay@gmail.com");
    issueResolutionService.addAgent("ag@gmail.com", "ayush", Arrays.asList(IssueType.FNO, IssueType.UPI));

    issueResolutionService.assignIssue(1);

    issueResolutionService.getIssue(new FilterIssueRequest(FilterType.ISSUE_ID, 1));
    issueResolutionService.getIssue(new FilterIssueRequest(FilterType.EMAIL_ID, "ayush@gmail.com"));

    issueResolutionService.updateIssue(1, IssueStatus.BLOCKED, "Not able to resolve");
    issueResolutionService.resolveIssue(1, "resolved");

    issueResolutionService.viewAgentsWorkHistory(1);
  }
}
