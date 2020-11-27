package pl.edu.wszib.book.store.database.impl;

import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersRepositoryImpl implements iUsersRepository {

    private final List<User> users = new ArrayList<>();

    public UsersRepositoryImpl() {
        this.users.add(new User("mateusz", "mateusz"));
        this.users.add(new User("admin", "admin"));
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

    @Override
    public boolean Register(User user) {
        if(this.Authenticate(user)==null){
            this.users.add(user);
            return true;
        }
        return false;
    }
}