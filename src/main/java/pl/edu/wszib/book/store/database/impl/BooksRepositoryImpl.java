package pl.edu.wszib.book.store.database.impl;

import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BooksRepositoryImpl implements iBooksRepository {

    private final List<Book> books = new ArrayList<>();

    public BooksRepositoryImpl() {
        this.books.add(new Book(0,"Czysty Kod","Robert C. Martin","11111",34.50,10));
        this.books.add(new Book(1,"Python w Zadaniach","Urszula, Adrian","22222",23.94,15));
        this.books.add(new Book(2,"Test","Nikt","00000",12.34,0));
        this.books.add(new Book(3,"Blockchain","Tomasz Waszczyk","33333",33.50,3));
        this.books.add(new Book(5,"Amazon Web","Piotr Kostka","44444",199.00,5));
    }

    @Override
    public List<Book> getAllBooks() {
        return this.books;
    }

    @Override
    public Book getBookByISBN(String isbn) {
        for(Book book : this.books){
            if (book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }

    @Override
    public Book getBookByID(int ID) {
        for(Book book : this.books){
            if (book.getId()==ID){
                return book;
            }
        }
        return null;
    }

    @Override
    public void updateBook(Book book) {
    }
}
