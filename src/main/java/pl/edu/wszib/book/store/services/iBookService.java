package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface iBookService {

    List<Book> getAllBooks();
    Book getBookByID(int ID);
    void updateBook(Book book);
}
