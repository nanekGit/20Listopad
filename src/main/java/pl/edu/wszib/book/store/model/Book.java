package pl.edu.wszib.book.store.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int pieces;

    private Book() {
    }

    public Book(String title, String author, String isbn, double price, int pieces) {
        this.id = 0;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.pieces = pieces;
    }

    public Book(int id, String title, String author, String isbn, double price, int pieces) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.pieces = pieces;
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
