package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.services.iBasketService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    iBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/koszyk", method = RequestMethod.GET)
    public String stronaKoszyk(Model model){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        model.addAttribute("isLogged", sessionObject.isLogged());
        model.addAttribute("books", sessionObject.getBasket());
        model.addAttribute("suma", basketService.calculateTotal());
        return "koszyk";
    }

    @RequestMapping(value = "/addToBasket/{id}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable int id){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        this.basketService.addBookByIdToBasket(id);
        return "redirect:/main";
    }
}
