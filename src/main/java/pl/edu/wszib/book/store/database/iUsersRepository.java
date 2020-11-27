package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.User;

public interface iUsersRepository {

    User Authenticate(User user);
    boolean Register(User user);
}
