package com.example.user.films.response;

import com.example.user.films.People;

import java.util.List;

/**
 * Created by sandr on 7/11/2018.
 */

public class PeopleResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<People> results;

    public PeopleResponse(Integer count, String next, String previous, List<People> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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

    public List<People> getResults() {
        return results;
    }

    public void setResults(List<People> results) {
        this.results = results;
    }
}
