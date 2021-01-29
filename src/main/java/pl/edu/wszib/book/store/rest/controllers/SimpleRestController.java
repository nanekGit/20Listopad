package pl.edu.wszib.book.store.rest.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.model.enums.Role;

@RestController
@RequestMapping(value = "/api")
public class SimpleRestController {

    @RequestMapping(value = "/endpoint1", method = RequestMethod.POST)
    public void endpoint1(@RequestBody User user){
        System.out.println(user);
    }

    @RequestMapping(value = "/endpoint2", method = RequestMethod.GET)
    public User endpoint2(){
        return new User(1,"1","1", Role.USER);
    }

    @RequestMapping(value = "/endpoint3/{id}", method = RequestMethod.GET)
    public User endpoint3(@PathVariable int id, @RequestBody User user){
        System.out.println("Poszło "+id);
        return new User(13,"13","13", Role.ADMIN);
    }

    @RequestMapping(value = "/endpoint4", method = RequestMethod.DELETE)
    public void endpoint4(){
        System.out.println("DELETE");
    }
}
