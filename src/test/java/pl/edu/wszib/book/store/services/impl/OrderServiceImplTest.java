package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.iOrderDAO;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.services.iOrderService;

@Service
public class OrderServiceImplTest implements iOrderService {

    @Autowired
    iOrderDAO iOrderDAO;

    @Override
    public void saveOrder(Order order) {
        this.iOrderDAO.saveOrder(order);
    }

    @Override
    public Order getOrderById(int id) {
        return this.iOrderDAO.getOrderById(id);
    }
}
