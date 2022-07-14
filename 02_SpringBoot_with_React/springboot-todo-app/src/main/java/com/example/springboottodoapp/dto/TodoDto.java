package com.example.springboottodoapp.dto;

import com.example.springboottodoapp.model.TodoEntity;
import lombok.*;

@Getter
@NoArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private boolean done;

    @Builder
    public TodoDto(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }
    
    // 응답받은 TodoDto를 TodoEntity로 변환하여 저장하기 위한 toEntity메서드
    public static TodoEntity toEntity(final TodoDto todoDto) {
        return TodoEntity.builder()
                .id(todoDto.getId())
                .title(todoDto.getTitle())
                .done(todoDto.isDone())
                .build();
    }
}
