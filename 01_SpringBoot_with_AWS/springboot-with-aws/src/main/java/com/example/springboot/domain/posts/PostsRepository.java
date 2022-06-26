package com.example.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 인터페이스 생성 후 JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적으로 CRUD 메서드가 생긴다
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
