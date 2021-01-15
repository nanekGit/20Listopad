package pl.edu.wszib.book.store.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.iBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.List;

@Repository
public class HibernateBookDAOImpl implements iBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Book");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public Book getBookByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Book WHERE id = :id");
        query.setParameter("id", id);
        Book result = null;
        try{
            result = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }
}
