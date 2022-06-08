package dev.cooremans.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private String timeStamp;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timeStamp = LocalDateTime.now().toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", message=" + message +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
