package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.User;

@Controller
public class UserController {

    @Autowired
    iUsersRepository usersRepository;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginForm(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginSubmit(@RequestParam String login,
                              @RequestParam String pass){
        User user = new User(login,pass);
        if(this.usersRepository.authenticate(user)){
            return "redirect:/main";
        }else{
            return "login";
        }
    }
}
