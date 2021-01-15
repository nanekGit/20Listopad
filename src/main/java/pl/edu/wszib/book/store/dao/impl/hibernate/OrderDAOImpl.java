package pl.edu.wszib.book.store.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.iOrderDAO;
import pl.edu.wszib.book.store.model.Order;

@Repository
public class OrderDAOImpl implements iOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Order WHERE id = :id");
        query.setParameter("id", id);
        Order result = null;
        try{
            result = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        session.close();
        return result;
    }
}
