package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PrincipalDetailService principalDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${link.key}")
    private String linkKey;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code, Model model) {

        RestTemplate rt = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "28a504d1c34f880e2bdfff575c2804ec");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);

        ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class);

        ObjectMapper ob = new ObjectMapper();
        OAuthToken oauthToken = null;

        try {
            oauthToken = ob.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("카카오 엑세스 토큰: " + oauthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders header2 = new HttpHeaders();
        header2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
//        header2.add("Content-type", "application/x-www-form-urlencoded");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(header2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class);

        log.info("kakao profile :" + response2.getBody());

        ObjectMapper ob2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = ob2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        log.info("카카오 가입자 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        log.info("카카오 가입자 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        User user = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(linkKey)
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();

        User originUser = userService.findUser(user.getUsername());

        if (originUser.getUsername() == null) {
            log.info("회원가입을 진행합니다. 아이디: " + kakaoProfile.getId());
            userService.save(user);
        }


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), linkKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication != null) {
            log.info("Authentication 객체가 정상적으로 저장되었습니다.");
            log.info("Principal: " + authentication.getPrincipal());
            log.info("Authorities : " + authentication.getAuthorities());
        } else {
            log.info("객체가 없습니다.");
        }

        model.addAttribute("principal", authentication.getPrincipal());



        return "redirect:/";
    }
}
