package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.iUsersRepository;
import pl.edu.wszib.book.store.model.Role;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.model.view.RegistrationModel;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        model.addAttribute("isLogged", sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@ModelAttribute User user){
        this.sessionObject.setLoggedUser(this.usersRepository.Authenticate(user));
        if(!this.sessionObject.isLogged()){
            return "redirect:http://localhost:8080/login";
        }
        return "redirect:/main";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.sessionObject.setLoggedUser(null);
        return "redirect:http://localhost:8080/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        model.addAttribute("RegistrationModel", new RegistrationModel());
        model.addAttribute("isLogged", sessionObject.isLogged());
        model.addAttribute("info", sessionObject.getInfo());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute RegistrationModel registrationModel){
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        Pattern regexp = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher loginMatcher = regexp.matcher(registrationModel.getLogin());
        Matcher passMatcher = regexp.matcher(registrationModel.getPass());
        Matcher pass2Matcher = regexp.matcher(registrationModel.getPass2());

        if(registrationModel.getPass().equals(registrationModel.getPass2()) &&
                loginMatcher.matches() &&
                passMatcher.matches() &&
                pass2Matcher.matches())
        {
            if(this.usersRepository.Register(new User(registrationModel.getLogin(),
                    registrationModel.getPass(),
                    Role.USER))){
                return "redirect:http://localhost:8080/login";
            }else{
                this.sessionObject.setInfo("Login Zajęty");
            }
        }else{
            this.sessionObject.setInfo("Błąd Validacji");
        }
        return "redirect:http://localhost:8080/register";
    }

    /*
    Stara wersja gdy formularz zwracał logowanie z pomocą name=""

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@RequestParam String login,
                              @RequestParam String pass){
        User user = new User(login,pass);
        if(this.usersRepository.Authenticate(user)){
            return "redirect:/main";
        }else{
            return "login";
        }
    }
    */
}
