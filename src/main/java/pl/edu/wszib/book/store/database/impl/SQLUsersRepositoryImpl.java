package pl.edu.wszib.book.store.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class SQLUsersRepositoryImpl implements iUsersRepository {

    @Autowired
    Connection connection;

    private final List<User> users = new ArrayList<>();

    @Override
    public User Authenticate(User user) {
        for(User userDB : this.users){
            if(userDB.getLogin().equals(user.getLogin())
                    && userDB.getPass().equals(user.getPass())){
                return userDB;
            }
        }
        return null;
    }

    @Override
    public boolean Register(User user) {
        if(this.isLoginInDB(user.getLogin())){
            return false;
        }
        this.users.add(user);
        return true;
    }

    private boolean isLoginInDB(String login){
        for(User userDB : this.users){
            if(userDB.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}
