package com.learn.models;

public class User {

    String username;
    String password;
    String email;

    String rolls;

    public String getRolls() {
        return rolls;
    }

    public void setRolls(String rolls) {
        this.rolls = rolls;
    }

    public User(){

    }

    public User(String username, String password, String email, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rolls=roles;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
