package pl.edu.wszib.book.store.database.impl;

import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.enums.Role;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;


public class UsersRepositoryImpl implements iUsersRepository {

    private final List<User> users = new ArrayList<>();

    public UsersRepositoryImpl() {
        this.users.add(new User(0,"mateusz", "mateusz", Role.USER));
        this.users.add(new User(1,"admin", "admin", Role.ADMIN));
    }

    @Override
    public User authenticate(User user) {
        for(User userDB : this.users){
            if(userDB.getLogin().equals(user.getLogin())
                    && userDB.getPass().equals(user.getPass())){
                return userDB;
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
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
