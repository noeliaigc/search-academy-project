package co.empathy.academy.search;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name){
        return "Hello " + name;
    }


}
