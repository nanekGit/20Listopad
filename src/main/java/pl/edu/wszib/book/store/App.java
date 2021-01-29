package pl.edu.wszib.book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import pl.edu.wszib.book.store.model.User;

import java.util.HashMap;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        //String url="http://localhost:8080/api/endpoint2";

        //RestTemplate restTemplate = new RestTemplate();
        //User user = restTemplate.getForObject(url, User.class, new HashMap<>());

        //System.out.println(user);

        SpringApplication.run(App.class,args);
    }
}
