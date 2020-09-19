package org.sda.twitter.model;

public class User {
    private String login;
    private String password;
    private int id;

    public int getId() { return id; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id, String login, String password ) {
        this.login = login;
        this.password = password;
        this.id = id;
    }
}
