package com.example.springboottodoapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="Todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : 기본 키 생성을 데이터베이스에 위임 (= AUTO_INCREMENT)
    private Long id;

    private String username;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done;

    @Builder
    public TodoEntity(Long id, String title, String username, boolean done) {
        super(); // 매개변수가 없는 생성자를 상속받음(NoArgsConstructor를 통해 생성됨)
        this.id = id;
        this.title = title;
        this.username = username;
        this.done = done;
    }

    // update용
    public void update(String title, String username, boolean done) {
        this.title = title;
        this.username = username;
        this.done = done;
    }
}
