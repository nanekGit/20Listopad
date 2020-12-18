package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface iBookDAO {

    List<Book> getAllBooks();
    Book getBookByID(int ID);
    void updateBook(Book book);
}
