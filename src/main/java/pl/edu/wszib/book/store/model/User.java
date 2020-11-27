package pl.edu.wszib.book.store.model;

public class User {
    private String login;
    private String pass;

    private User() {
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
