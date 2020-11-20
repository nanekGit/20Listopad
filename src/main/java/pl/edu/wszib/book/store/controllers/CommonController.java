package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

    @RequestMapping(value = "/cos", method = RequestMethod.GET)
    public String httpRequestAction(){
        System.out.println("1111111\n1111111\n1111111\n1111111");
        return "main";
    }

    @GetMapping(value = "/cos2") //to samo co request, tylko zawsze robi GET
    public String httpRequestAction2(){
        System.out.println("2222222\n2222222\n2222222\n2222222");
        return "main";
    }

    @RequestMapping(value = "/cos3", method = RequestMethod.GET)
    public String httpRequestAction3(){
        System.out.println("3333333\n33333333\n333333\n3333333");
        return "main";
    }

    @RequestMapping(value = "/test/z", method = RequestMethod.GET)
    public String httpRequestActionz(){
        System.out.println("zzzzzzz\nzzzzzzz\nzzzzzzz\nzzzzzzz");
        return "main";
    }
}
