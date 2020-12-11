package pl.edu.wszib.book.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@ComponentScan("pl.edu.wszib.book.store")
public class AppConfiguration {

    @Bean
    public Connection connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "");
        } catch (Exception e) {
        }
        return null;
    }
}
