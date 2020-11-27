package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

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
        List<Book> books=booksRepository.getAllBooks();
        model.addAttribute("books",books);
        model.addAttribute("isLogged",sessionObject.isLogged());
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String kontakt(Model model){
        model.addAttribute("isLogged",sessionObject.isLogged());
        return "contact";
    }

    @RequestMapping(value = "/XAMPP", method = RequestMethod.GET)
    public String testPage(){
        //experyment czy działa redirect
        return "redirect:https://localhost/phpmyadmin";
    }

    @RequestMapping(value = "/cos/{param1}/{param2}", method = RequestMethod.GET)
    public String httpRequestAction(@PathVariable String param1,
                                    @PathVariable String param2){
        System.out.println("1111111\n1111111\n1111111\n1111111");
        System.out.println(param1);
        System.out.println(param2);
        //http://127.0.0.1:8080/cos/param1/param2
        return "main";
    }

    @GetMapping(value = "/cos2") //to samo co request, tylko zawsze robi GET
    public String httpRequestAction2(@RequestParam String name,
                                     @RequestParam String surname){
        System.out.println("2222222\n2222222\n2222222\n2222222");
        System.out.println(name);
        System.out.println(surname);
        //http://127.0.0.1:8080/cos2?name=test&surname=zupa
        //Pierwszy z ? a każdy następny z &
        return "main";
    }
}
