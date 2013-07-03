package com.myfinances.home.viewmodels;

public class HomeViewModel {
    public HomeViewModel() {
        message = "Welcome, from the server side";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
