package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.User;

public interface iUserDAO {

    User getUserByLogin(String login);
    boolean persistUser(User user);
}
