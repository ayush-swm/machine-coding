package model;

public class FilterIssueRequest {
    private FilterType filterType;
    private Object filterValue;

    public FilterIssueRequest(FilterType filterType, Object filterValue) {
        this.filterType = filterType;
        this.filterValue = filterValue;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public Object getFilterValue() {
        return filterValue;
    }
}
