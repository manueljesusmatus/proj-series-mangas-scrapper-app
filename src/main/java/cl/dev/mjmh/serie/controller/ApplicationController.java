package cl.dev.mjmh.serie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/app")
public class ApplicationController {

    @GetMapping(path = "/health")
    public String health() {
        return "OK";
    }

}
