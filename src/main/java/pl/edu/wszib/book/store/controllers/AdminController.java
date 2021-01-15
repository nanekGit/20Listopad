package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.model.enums.Role;
import pl.edu.wszib.book.store.services.iBookService;
import pl.edu.wszib.book.store.services.iOrderService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    iBookService bookService;

    @Autowired
    iOrderService orderService;

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

    @RequestMapping(value = "/cheat", method = RequestMethod.GET)
    public String cheat(){
        List<Book> books = this.bookService.getAllBooks();

        Order order = new Order();
        order.setStatus(Order.Status.ORDERED);
        order.setUser(this.sessionObject.getLoggedUser());

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setPieces(1);
        orderPosition.setBook(books.get(0));
        orderPosition.setOrder(order);

        order.getPositions().add(orderPosition);

        OrderPosition orderPosition2 = new OrderPosition();
        orderPosition2.setPieces(3);
        orderPosition2.setBook(books.get(1));
        orderPosition2.setOrder(order);

        order.getPositions().add(orderPosition2);


        order.setPrice(books.get(0).getPrice()*1 + books.get(1).getPrice()*3);
        this.orderService.saveOrder(order);

        return "redirect:/main";
    }

    @RequestMapping(value = "/dupa", method = RequestMethod.GET)
    public String dupa(){
        Order order = this.orderService.getOrderById(1);
        System.out.println(order);
        return "redirect:/main";
    }
}
