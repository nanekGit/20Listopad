package pl.edu.wszib.book.store.dao.impl.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.book.store.dao.iUserDAO;
import pl.edu.wszib.book.store.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Jeszcze nie dodane
public class HibernateUserDAOImpl implements iUserDAO {

    @Autowired
    Connection connection;

    @Override
    public User getUserByLogin(String login) {
        try{
            String sql = "SELECT * FROM tuser WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new User(resultSet);
            }
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public boolean persistUser(User user) {
        try{
            String sql = "INSERT INTO tuser (login, pass, rola) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPass());
            preparedStatement.setString(3,user.getRola().toString());

            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
        }
        return false;
    }
}
