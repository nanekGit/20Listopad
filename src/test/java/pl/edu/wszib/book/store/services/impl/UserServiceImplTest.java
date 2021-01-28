package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.iUserDAO;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.model.enums.Role;
import pl.edu.wszib.book.store.model.view.RegistrationModel;
import pl.edu.wszib.book.store.services.iUserService;
import pl.edu.wszib.book.store.session.SessionObject;

@Service
public class UserServiceImplTest implements iUserService {

    @Autowired
    iUserDAO userDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public void authenticate(User user) {
        User userFromDB = userDAO.getUserByLogin(user.getLogin());
        if(userFromDB!=null && userFromDB.getPass().equals(user.getPass())) {
            this.sessionObject.setLoggedUser(userFromDB);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null){
            return false;
        }
        User newUser = new User(0, registrationModel.getLogin(),registrationModel.getPass(), Role.USER);
        return this.userDAO.persistUser(newUser);
    }
}
