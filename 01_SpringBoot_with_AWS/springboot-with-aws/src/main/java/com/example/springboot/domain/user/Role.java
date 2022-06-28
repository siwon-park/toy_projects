package com.example.springboot.domain.user;

import lombok.*;

// Enum 클래스
@Getter
@RequiredArgsConstructor
public enum Role {

    // 스프링 시큐리티에서는 권한 코드 앞에 항상 ROLE_ 이 있어야함
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
