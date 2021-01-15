package pl.edu.wszib.book.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "torder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderPosition> positions = new HashSet<>();
    private double price;
    @Enumerated(EnumType.STRING)
    private Order.Status status;

    public Order(){
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ",\n user=" + user +
                ",\n positions=" + positions +
                ",\n price=" + price +
                ",\n status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPositions(Set<OrderPosition> positions) {
        this.positions = positions;
    }

    public Set<OrderPosition> getPositions() {
        return positions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{
        ORDERED,
        ACCEPTED,
        SENT,
        ENDED
    }
}
