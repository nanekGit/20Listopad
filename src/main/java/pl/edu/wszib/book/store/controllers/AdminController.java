package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.iBooksRepository;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.enums.Role;
import pl.edu.wszib.book.store.services.iBookService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    iBookService bookService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        if (sessionObject.getLoggedUser().getRola() != Role.ADMIN){
            return "redirect:/main";
        }
        Book book = this.bookService.getBookByID(id);
        model.addAttribute("book",book);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getLoggedUser().getRola().toString());
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPage(@ModelAttribute Book book){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        if (sessionObject.getLoggedUser().getRola() != Role.ADMIN){
            return "redirect:/main";
        }
        this.bookService.updateBook(book);
        return "redirect:/main";
        //Stara wersja, wysyła do GET
        //return return "redirect:/edit/"+book.getIsbn();
    }
}
