package com.example.user.films;

import java.util.List;

public class PlanetsResponse {
    private int count;
    private String next;
    private String previous;
    private List<Planets> results;

    public PlanetsResponse(int count, String next, String previous, List<Planets> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Planets> getResults() {
        return results;
    }

    public void setResults(List<Planets> results) {
        this.results = results;
    }
}
