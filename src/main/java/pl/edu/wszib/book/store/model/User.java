package pl.edu.wszib.book.store.model;

public class User {

    private int id;
    private String login;
    private String pass;
    private Role rola;

    public User() {
    }

    public User(String login, String pass, Role rola) {
        this.login = login;
        this.pass = pass;
        this.rola = rola;
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

    public Role getRola() {
        return rola;
    }

    public void setRola(Role rola) {
        this.rola = rola;
    }
}
