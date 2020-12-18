package pl.edu.wszib.book.store.model;

import pl.edu.wszib.book.store.model.enums.Role;
import java.sql.ResultSet;

public class User {

    private int id;
    private String login;
    private String pass;
    private Role rola;

    public User(int id, String login, String pass, Role rola) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.rola = rola;
    }

    public User(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.login = resultSet.getString("login");
            this.pass = resultSet.getString("pass");
            this.rola = Role.valueOf(resultSet.getString("rola"));
        } catch (Exception e) {
        }
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
