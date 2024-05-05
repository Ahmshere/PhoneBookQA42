package models;

public class ErrorModel {
    int status;
    String error;
    Object message;

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
