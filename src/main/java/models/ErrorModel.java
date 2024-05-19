package models;

import lombok.Builder;


public class ErrorModel {
    String timestamp;
    int status;
    String error;
    Object message;
    String path;

    public ErrorModel(String timestamp, int status, String error, Object message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ErrorModel() {
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message=" + message +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
