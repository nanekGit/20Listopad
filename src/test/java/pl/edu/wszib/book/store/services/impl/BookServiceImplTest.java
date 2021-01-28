package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.iBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.iBookService;

import java.util.List;

@Service
public class BookServiceImplTest implements iBookService {

    @Autowired
    iBookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    @Override
    public Book getBookByID(int ID) {
        return this.bookDAO.getBookByID(ID);
    }

    @Override
    public void updateBook(Book book) {
        Book bookFromDB = this.bookDAO.getBookByID(book.getId());
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setIsbn(book.getIsbn());
        bookFromDB.setPieces(book.getPieces());
        bookFromDB.setPrice(book.getPrice());
        this.bookDAO.updateBook(bookFromDB);
    }
}
