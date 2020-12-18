package pl.edu.wszib.book.store.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionObject {

    private User loggedUser = null;
    private String info = null;
    private final List<Book> basket = new ArrayList<>();

    public boolean isLogged(){
        return this.loggedUser != null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getInfo() {
        String temp = this.info;
        this.info = null;
        return temp;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Book> getBasket() {
        return basket;
    }

    public void addToBasket(Book book){
        for(Book bookFromBasket : basket){
            if(bookFromBasket.getId()==book.getId()){
                bookFromBasket.setPieces(bookFromBasket.getPieces()+1);
                return;
            }
        }
        book.setPieces(1);
        basket.add(book);
    }
}
