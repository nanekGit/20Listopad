package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    iBooksRepository booksRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(){
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("books", this.booksRepository.getAllBooks());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        if(this.sessionObject.isLogged()) {
            model.addAttribute("role", this.sessionObject.getLoggedUser().getRola().toString());
        }else{
            model.addAttribute("role", null);
        }
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String kontakt(Model model){
        model.addAttribute("isLogged",this.sessionObject.isLogged());
        return "contact";
    }

    @RequestMapping(value = "/XAMPP", method = RequestMethod.GET)
    public String testPage(){
        //experyment czy działa redirect
        return "redirect:http://localhost/phpmyadmin";
    }

    @RequestMapping(value = "/cos/{param1}/{param2}", method = RequestMethod.GET)
    public String httpRequestAction(@PathVariable String param1,
                                    @PathVariable String param2,
                                    Model model){
        System.out.println("1111111\n1111111\n1111111\n1111111");
        System.out.println(param1);
        System.out.println(param2);
        model.addAttribute("isLogged",this.sessionObject.isLogged());
        //http://127.0.0.1:8080/cos/param1/param2
        return "contact";
    }

    @GetMapping(value = "/cos2") //to samo co request, tylko zawsze robi GET
    public String httpRequestAction2(@RequestParam String name,
                                     @RequestParam String surname,
                                     Model model){
        System.out.println("2222222\n2222222\n2222222\n2222222");
        System.out.println(name);
        System.out.println(surname);
        model.addAttribute("isLogged",this.sessionObject.isLogged());
        //http://127.0.0.1:8080/cos2?name=test&surname=zupa
        //Pierwszy z ? a każdy następny z &
        return "contact";
    }
}
