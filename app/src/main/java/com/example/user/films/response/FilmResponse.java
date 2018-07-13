package com.example.user.films.response;

import com.example.user.films.Films;

import java.util.List;

public class FilmResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<Films> results;

    public FilmResponse(Integer count, String next, String previous, List<Films> results) {
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

    public List<Films> getResults() {
        return results;
    }

    public void setResults(List<Films> results) {
        this.results = results;
    }
}
