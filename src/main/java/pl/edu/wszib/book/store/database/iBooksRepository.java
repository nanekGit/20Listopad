package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface iBooksRepository {

    List<Book> getAllBooks();
    Book getBookByISBN(String isbn);
}
