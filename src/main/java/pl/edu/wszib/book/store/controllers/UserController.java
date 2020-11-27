package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    iUsersRepository usersRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model){
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@ModelAttribute User user){
        this.sessionObject.setLoggedUser(this.usersRepository.authenticate(user));
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        return "redirect:http://localhost:8080/login";
    }

    @RequestMapping(value = "/koszyk", method = RequestMethod.GET)
    public String stronaKonta(){
        if(this.sessionObject.isLogged()){
            return "contact";
        }
        return "redirect:http://localhost:8080/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.sessionObject.setLoggedUser(null);
        return "redirect:http://localhost:8080/login";
    }

    /*
    Stara wersja gdy formularz zwracał logowanie z pomocą name=""

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@RequestParam String login,
                              @RequestParam String pass){
        User user = new User(login,pass);
        if(this.usersRepository.authenticate(user)){
            return "redirect:/main";
        }else{
            return "login";
        }
    }
    */
}
