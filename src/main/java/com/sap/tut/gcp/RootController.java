package com.sap.tut.gcp;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;


@Controller
public class RootController {

    @GetMapping("/")
    @ResponseBody
    public
    Flux<String> getRoot() {
        return Flux.just("Alive");
    }
    
    @GetMapping("/hello")
    @ResponseBody
    public
    Flux<String> getHello() {
    	return Flux.just("hello..." + new Date());
    }

}
