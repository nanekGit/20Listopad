package pl.edu.wszib.book.store.model;

import javax.persistence.*;
import java.sql.ResultSet;

@Entity(name = "tbook")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int pieces;

    public Book(int id, String title, String author, String isbn, double price, int pieces) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.pieces = pieces;
    }

    public Book(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.title = resultSet.getString("title");
            this.author = resultSet.getString("author");
            this.isbn = resultSet.getString("isbn");
            this.price = resultSet.getDouble("price");
            this.pieces = resultSet.getInt("pieces");
        } catch (Exception e) {
        }
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ",\n title='" + title + '\'' +
                ",\n author='" + author + '\'' +
                ",\n isbn='" + isbn + '\'' +
                ",\n price=" + price +
                ",\n pieces=" + pieces +
                '}';
    }

    public Book clone(){
        return new Book(this.id, this.title, this.author, this.isbn, this.price, this.pieces);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
