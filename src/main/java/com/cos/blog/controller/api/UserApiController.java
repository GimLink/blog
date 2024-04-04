package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        log.info("UserApiController.save 호출됨");
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
}
