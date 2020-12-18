package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.iBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.iBasketService;
import pl.edu.wszib.book.store.session.SessionObject;

@Service
public class BasketServiceImpl implements iBasketService {

    @Autowired
    iBookDAO bookDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public double calculateTotal() {
        double sum = 0.0;
        for(Book book : this.sessionObject.getBasket()){
            sum += book.getPrice()*book.getPieces();
        }
        return sum;
    }

    @Override
    public void addBookByIdToBasket(int id) {
        Book book=bookDAO.getBookByID(id);
        for(Book bookFromBasket : sessionObject.getBasket()){
            if(bookFromBasket.getId()==book.getId()){
                bookFromBasket.setPieces(bookFromBasket.getPieces()+1);
                return;
            }
        }
        book.setPieces(1);
        sessionObject.getBasket().add(book);
    }
}
