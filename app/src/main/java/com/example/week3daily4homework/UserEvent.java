package com.example.week3daily4homework;

public class UserEvent {
    private UserResponse userResponse;

    public UserEvent(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
