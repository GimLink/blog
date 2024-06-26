package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable long id) {
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 id는 DB에 존재하지 않습니다.";
        }
        return "삭제되었습니다. id = " + id;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User requestUser) {
        log.info("user = " + requestUser);

        User user = userRepository.findById(id).orElseThrow(()-> {return new IllegalArgumentException("수정에 실패하였습니다");});
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> userList() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " );
            }
        });
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        log.info("join: {}, {}, {}", user.getUsername(), user.getPassword(), user.getEmail());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
