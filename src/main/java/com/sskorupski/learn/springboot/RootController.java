package com.sskorupski.learn.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping(value = "/")
    public String index() {
        return "Hello Spring JDBC";
    }

}
