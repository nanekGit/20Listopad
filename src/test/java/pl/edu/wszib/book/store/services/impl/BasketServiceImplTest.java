package pl.edu.wszib.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.book.store.configuration.AppConfigurationTest;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.iBasketService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
@WebAppConfiguration
public class BasketServiceImplTest{

    @Autowired
    iBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @Test
    public void calculateTotalTest(){
        Assert.assertNotNull(basketService);
        sessionObject.getBasket().add(new Book(1,"1","1","1",12.34,5));
        sessionObject.getBasket().add(new Book(2,"1","1","1",32.34,2));
        sessionObject.getBasket().add(new Book(3,"1","1","1",44.34,1));

        double expected = 5*12.34 + 2*32.34 + 1*44.34;
        double result = this.basketService.calculateTotal();

        Assert.assertEquals(expected,result,0.01);
    }
}
