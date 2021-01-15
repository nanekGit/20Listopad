package pl.edu.wszib.book.store.database.impl.sql;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.enums.Role;
import pl.edu.wszib.book.store.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SQLUsersRepositoryImpl implements iUsersRepository {

    @Autowired
    Connection connection;

    @Override
    public User authenticate(User user) {
        try{
            String sql = "SELECT * FROM tuser WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                if(user.getPass().equals(resultSet.getString("login"))){
                    return new User(resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("pass"),
                            Role.valueOf(resultSet.getString("rola")));
                }
            }
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        if(this.isLoginInDB(user.getLogin())){
            return false;
        }
        try{
            String sql = "INSERT INTO tuser (login, pass, role) VALUES (?, ?, ?);";
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

    private boolean isLoginInDB(String login){
        try{
            String sql = "SELECT * FROM tuser WHERE login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                return false;
            }
        }catch (Exception e){
        }
        return true;
    }
}
