package pl.edu.wszib.book.store.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.book.store.configuration.AppConfigurationTest;
import pl.edu.wszib.book.store.dao.iBookDAO;
import pl.edu.wszib.book.store.dao.iOrderDAO;
import pl.edu.wszib.book.store.dao.iUserDAO;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.model.enums.Role;
import pl.edu.wszib.book.store.model.view.RegistrationModel;
import pl.edu.wszib.book.store.services.iUserService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    iUserService userService;

    @MockBean
    iBookDAO bookDAO;

    @MockBean
    iUserDAO userDAO;

    @MockBean
    iOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Before
    public void configureMock(){
        /*
        Konfiguracja mocka w jednym miejscu zamiast w każdym teście
        Mockito.when(this.userDAO.getUserByLogin("Karol")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(this.userDAO.getUserByLogin("Karol1")).thenReturn(new User());
        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(generateUser());
        Mockito.when(this.userDAO.getUserByLogin("mateusz1")).thenReturn(null);
        */
    }

    @Test
    public void testRegister(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("Karol");
        registrationModel.setPass("Karol");
        registrationModel.setPass2("Karol");

        Mockito.when(this.userDAO.getUserByLogin("Karol")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);
        boolean result = userService.register(registrationModel);

        Assert.assertTrue(result);
    }

    @Test
    public void testRegisterIncorrectLogin(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("Karol1");
        registrationModel.setPass("Karol1");
        registrationModel.setPass2("Karol1");

        Mockito.when(this.userDAO.getUserByLogin("Karol1")).thenReturn(new User());
        boolean result = userService.register(registrationModel);

        Assert.assertFalse(result);
    }

    @Test
    public void testCorrectAuthenticate(){
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("mateusz");

        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(generateUser());

        this.userService.authenticate(user);

        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    private User generateUser(){
        return new User(5, "mateusz", "mateusz", Role.USER);
    }

    @Test
    public void testInCorrectAuthenticate(){
        User user = new User();
        user.setLogin("mateusz1");
        user.setPass("mateusz1");

        Mockito.when(this.userDAO.getUserByLogin("mateusz1")).thenReturn(null);

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void testIncorrectPassword() {
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("mateusz1");

        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(generateUser());

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }


}
