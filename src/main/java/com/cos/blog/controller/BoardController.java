package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
        log.info("로그인 사용자 아이디 : " + principal.getUsername());
        return "index";
    }
}
