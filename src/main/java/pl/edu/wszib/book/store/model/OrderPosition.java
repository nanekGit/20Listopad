package pl.edu.wszib.book.store.model;

import javax.persistence.*;

@Entity(name = "toderposition")
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pieces;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    public OrderPosition(){
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "id=" + id +
                ",\n pieces=" + pieces +
                ",\n book=" + book +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
