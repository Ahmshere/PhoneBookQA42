package models;

import io.qameta.allure.Step;

public class NewUserModel {

    String username;
    String password;

    @Override
    public String toString() {
        return "NewUserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public NewUserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Step("Getting user name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
