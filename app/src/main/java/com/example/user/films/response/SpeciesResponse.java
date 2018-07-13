package com.example.user.films.response;

import com.example.user.films.Species;

import java.util.List;

public class SpeciesResponse {
    private int count;
    private String next;
    private String previous;
    private List<Species> results;

    public SpeciesResponse(int count, String next, String previous, List<Species> results) {
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

    public List<Species> getResults() {
        return results;
    }

    public void setResults(List<Species> results) {
        this.results = results;
    }
}
