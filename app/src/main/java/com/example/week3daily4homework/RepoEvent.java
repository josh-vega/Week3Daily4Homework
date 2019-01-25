package com.example.week3daily4homework;

public class RepoEvent {
    private RepoResponse response;

    public RepoEvent(RepoResponse response) {
        this.response = response;
    }

    public RepoResponse getResponse() {
        return response;
    }

    public void setResponse(RepoResponse response) {
        this.response = response;
    }
}
