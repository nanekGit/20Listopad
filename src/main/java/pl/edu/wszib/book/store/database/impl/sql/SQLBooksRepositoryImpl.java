package pl.edu.wszib.book.store.database.impl.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLBooksRepositoryImpl implements iBooksRepository {

    @Autowired
    Connection connection;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tbook;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                books.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("pieces")));
            }
        }catch (Exception e){
        }
        return books;
    }

    @Override
    public Book getBookByISBN(String isbn) {
        try{
            String sql = "SELECT * FROM tbook WHERE isbn = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("pieces"));
            }
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public Book getBookByID(int ID) {
        try{
            String sql = "SELECT * FROM tbook WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("pieces"));
            }
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public void updateBook(Book book) {
        try{
            String sql = "UPDATE tbook SET title = ?, author = ?, isbn = ?, price = ?, pieces = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getIsbn());
            preparedStatement.setDouble(4,book.getPrice());
            preparedStatement.setInt(5,book.getPieces());
            preparedStatement.setInt(6,book.getId());

            preparedStatement.executeUpdate();
        }catch (Exception e){
        }
    }
}
