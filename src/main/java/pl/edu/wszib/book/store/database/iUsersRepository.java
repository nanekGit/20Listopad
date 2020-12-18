package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.User;

public interface iUsersRepository {

    User authenticate(User user);
    boolean register(User user);
}
