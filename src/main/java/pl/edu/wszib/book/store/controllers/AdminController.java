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
import pl.edu.wszib.book.store.model.Role;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    iBooksRepository booksRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.GET)
    public String edit(@PathVariable String isbn, Model model){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        if (sessionObject.getLoggedUser().getRola() != Role.ADMIN){
            return "redirect:/main";
        }
        Book book = booksRepository.getBookByISBN(isbn);
        model.addAttribute("book",book);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getLoggedUser().getRola().toString());
        return "edit";
    }

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.POST)
    public String editPage(@ModelAttribute Book book,Model model){
        if (!sessionObject.isLogged()) {
            return "redirect:http://localhost:8080/login";
        }
        if (sessionObject.getLoggedUser().getRola() != Role.ADMIN){
            return "redirect:/main";
        }
        Book bookFromDB = this.booksRepository.getBookByISBN(book.getIsbn());
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setPieces(book.getPieces());
        bookFromDB.setPrice(book.getPrice());
        return "redirect:/main";
        //Stara wersja, wysyła do GET
        //return return "redirect:/edit/"+book.getIsbn();
    }
}
