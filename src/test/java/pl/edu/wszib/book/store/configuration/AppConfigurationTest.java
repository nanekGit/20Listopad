package pl.edu.wszib.book.store.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.book.store.dao.iBookDAO;
import pl.edu.wszib.book.store.dao.iOrderDAO;
import pl.edu.wszib.book.store.dao.iUserDAO;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.book.store.controllers",
        "pl.edu.wszib.book.store.services.impl",
        "pl.edu.wszib.book.store.session"
})
public class AppConfigurationTest {

    /*
    To działa na wszystkie testy, trzeba oddzielny mock na każdy inny test

    Zamiast tego można MockBean dać aby używać wybrane i mockito.when aby konfigurować

    @Bean
    public iBookDAO bookDAO(){
        return Mockito.mock(iBookDAO.class);
    }

    @Bean
    public iUserDAO userDAO(){
        return Mockito.mock(iUserDAO.class);
    }

    @Bean
    public iOrderDAO orderDAO(){
        return Mockito.mock(iOrderDAO.class);
    }*/
}
