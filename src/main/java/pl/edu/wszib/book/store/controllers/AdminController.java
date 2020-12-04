package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    iBooksRepository booksRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable String isbn){
        //TODO na razie nic tu nie ma
        this.sessionObject.addToBasket(booksRepository.getBookByISBN(isbn).clone());
        if (sessionObject.isLogged()) {
            return "redirect:/main";
        }
        return "redirect:http://localhost:8080/login";
    }
}
