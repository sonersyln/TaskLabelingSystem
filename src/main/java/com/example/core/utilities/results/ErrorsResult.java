package com.example.core.utilities.results;

public class ErrorsResult extends Result {


    public ErrorsResult(String message) {
        super(message, false);
    }

    public ErrorsResult() {
        super(false);
    }
}
