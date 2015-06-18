package com.nbcuni.test.publisher.bo;

/**
 * User component to perform login operation
 */


public class User {

    private final String user;
    private final String password;

    public User(String name, String pass) {
        this.user = name;
        this.password = pass;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
