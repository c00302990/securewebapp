package com.changmin.securewebapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cors-test")
public class CorsTestController {
    @CrossOrigin(
            origins = "https://securewebapp-eight.vercel.app",
            methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST},
            allowCredentials = "false"
    )

    @GetMapping
    public String test(){
        return "CORS OK";
    }
}