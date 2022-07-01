package com.example.springboottodoapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done;

    @Builder
    public Todo(String title, String username, boolean done) {
        super(); // 매개변수가 없는 생성자를 상속받음(NoArgsConstructor를 통해 생성됨)
        this.title = title;
        this.username = username;
        this.done = done;
    }
}
