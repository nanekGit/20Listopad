package pl.edu.wszib.book.store.services;

public interface iBasketService {

    double calculateTotal();
    void addBookByIdToBasket(int id);
}
