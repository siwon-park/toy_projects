package com.example.springboottodoapp.security;

import com.example.springboottodoapp.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

// 사용자 정보를 받아서 JWT를 생성하는 클래스
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${jwt.password}")
    private String secretKey; // 시크릿 키 설정(문자열 아무거나 지정후 인코딩하여 사용할 예정)

//    @PostConstruct
//    protected void init() {
//        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
//    }

    // 토큰 생성 메서드
    public String createJWT(UserEntity userEntity) {

        // 만료 기한 지금부터 1일로 설정
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                // 헤더의 타입(typ)을 지정. jwt를 사용하기 때문에 Header.JWT_TYPE를 적용
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
                // 페이로드에 들어갈 내용들
                // 토큰 발급자를 설정(앱이름으로 설정하였음)
                .setIssuer("SpringbootTodoApp")
                // 발급 시간 설정(Date 타입만 가능)
                .setIssuedAt(new Date())
                // 만료 시간 설정(Date 타입만 가능)
                .setExpiration(expiryDate)
                // 토큰 제목
                .setSubject(userEntity.getId())
                // 비공개 클레임(key-value)설정(민감한 정보는 claim에 넣어선 안 됨)
//                .claim("email", "zow777@naver.com")
                .compact();
    }

    // 토큰 검증 메서드(검증 후, userId를 반환)
    public String validateAndGetUserId(String token) {
        // 위조되지 않았다면 페이로드(Claims)를 반환하고, 위조라면 예외를 날림
        Claims claims = Jwts.parser()
                // 헤더와 페이로드를 setSigningKey로 넘어온 SECRET_KEY를 이용해 서명한 후 token의 서명과 비교
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())) // getBytes() 필수
                // parseClaimJws 메서드가 Base64로 디코딩 및 파싱함
                .parseClaimsJws(token)
                // 검증 후 반환받는 값이 userId이므로 getBody를 사용함
                .getBody();
        
        return claims.getSubject();
    }

    // 토큰의 앞 부분인 Bearer를 제거하는 메서드
    private String removeBearer(String token) {
        return token.substring("Bearer ".length());
    }
}
