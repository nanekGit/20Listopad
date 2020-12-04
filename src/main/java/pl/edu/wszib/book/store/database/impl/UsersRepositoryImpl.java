package pl.edu.wszib.book.store.database.impl;

import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.Role;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersRepositoryImpl implements iUsersRepository {

    private final List<User> users = new ArrayList<>();

    public UsersRepositoryImpl() {
        this.users.add(new User("mateusz", "mateusz", Role.USER));
        this.users.add(new User("admin", "admin", Role.ADMIN));
    }

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

    private boolean isLoginInDB(String login){
        for(User userDB : this.users){
            if(userDB.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean Register(User user) {
        if(this.isLoginInDB(user.getLogin())){
            return false;
        }
        this.users.add(user);
        return true;
    }
}
