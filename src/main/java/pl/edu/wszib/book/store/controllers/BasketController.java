package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    iBooksRepository booksRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/koszyk", method = RequestMethod.GET)
    public String stronaKoszyk(Model model){
        model.addAttribute("isLogged", sessionObject.isLogged());
        model.addAttribute("books", sessionObject.getBasket());
        double sum = 0;
        for(Book book : this.sessionObject.getBasket()){
            sum += book.getPrice()*book.getPieces();
        }
        model.addAttribute("suma", sum);
        if(this.sessionObject.isLogged()){
            return "koszyk";
        }
        return "redirect:http://localhost:8080/login";
    }

    @RequestMapping(value = "/addToBasket/{isbn}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable String isbn){
        this.sessionObject.addToBasket(booksRepository.getBookByISBN(isbn).clone());
        if (sessionObject.isLogged()) {
            return "redirect:/main";
        }
        return "redirect:http://localhost:8080/login";
    }
}
