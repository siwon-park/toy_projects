package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.ResponseDto;
import com.example.springboottodoapp.dto.UserDto;
import com.example.springboottodoapp.model.UserEntity;
import com.example.springboottodoapp.security.TokenProvider;
import com.example.springboottodoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @PostMapping("/signup") // 회원가입
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            // user 생성
            UserEntity user = UserEntity.builder()
                    .email(userDto.getEmail())
                    .id(userDto.getId())
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .build();

            // 서비스를 이용해 user를 레포지토리에 저장함
            UserEntity registeredUser = userService.create(user);
            UserDto responseUserDto = UserDto.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(responseUserDto);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @PostMapping("/signin") // 로그인
    public ResponseEntity<?> authenticate(@RequestBody UserDto userDto) {
        UserEntity user = userService.getByCredentials(
                userDto.getEmail(),
                userDto.getPassword(),
                passwordEncoder);

        if (user != null) {
            // 유저 정보가 null이 아니면 토큰을 생성함
            final String token = tokenProvider.createJWT(user);
            final UserDto responseUserDto = UserDto.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .token(token) // 토큰을 넣어서 Dto를 생성함
                    .build();
            return ResponseEntity.ok().body(responseUserDto);
        } else {
            ResponseDto responseDto = ResponseDto.builder()
                    .error("Login Failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
