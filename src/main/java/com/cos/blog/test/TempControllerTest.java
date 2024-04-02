package com.cos.blog.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        log.info("tempHome");
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        return "/PEPE.jpg";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        return "test";
    }
}
