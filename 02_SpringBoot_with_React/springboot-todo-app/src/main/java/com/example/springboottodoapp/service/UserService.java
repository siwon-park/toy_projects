package com.example.springboottodoapp.service;

import com.example.springboottodoapp.model.UserEntity;
import com.example.springboottodoapp.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        // 비밀번호를 encode하여 넣음(29~ 37번째 줄이 책과 다름)
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userPass = bCryptPasswordEncoder.encode(userEntity.getPassword());
        UserEntity newUsers = UserEntity.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userPass)
                .build();
        return userRepository.save(newUsers);
    }

    // 로그인 인증(패스워드 일치 여부 추가)
    public UserEntity getByCredentials(final String email, final String password, final BCryptPasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);
        System.out.println(password.equals(originalUser.getPassword())); // false(encode하지 않았다면 true)
        // 책에서는 회원가입에서 패스워드를 encode해서 넣어야하는데 그렇게 하지 않고있기 때문에 equals를 사용함
        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
//        return userRepository.findByEmailAndPassword(email, password);
        return null;
    }
}
