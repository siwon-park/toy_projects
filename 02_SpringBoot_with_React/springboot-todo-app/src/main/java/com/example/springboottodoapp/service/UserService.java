package com.example.springboottodoapp.service;

import com.example.springboottodoapp.model.UserEntity;
import com.example.springboottodoapp.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성(회원가입)
    public UserEntity create(final UserEntity userEntity) {
        if (userEntity == null || userEntity.getEmail() == null) {
            throw new RuntimeException("Invalid Arguments");
        }
        final String email = userEntity.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("Email Already Exists {}", email);
            throw new RuntimeException("Email Already Exists");
        }

        return userRepository.save(userEntity);
    }

    // 로그인 인증(패스워드 일치 여부 추가)
    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);
        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
//        return userRepository.findByEmailAndPassword(email, password);
        return null;
    }
}
