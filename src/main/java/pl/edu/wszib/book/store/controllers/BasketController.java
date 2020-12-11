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
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        model.addAttribute("isLogged", sessionObject.isLogged());
        model.addAttribute("books", sessionObject.getBasket());
        double sum = 0;
        for(Book book : this.sessionObject.getBasket()){
            sum += book.getPrice()*book.getPieces();
        }
        model.addAttribute("suma", sum);
        return "koszyk";
    }

    @RequestMapping(value = "/addToBasket/{isbn}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable String isbn){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        this.sessionObject.addToBasket(booksRepository.getBookByISBN(isbn).clone());
        return "redirect:/main";
    }
}
