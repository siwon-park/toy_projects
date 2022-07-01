package com.example.springboottodoapp.dto;

import com.example.springboottodoapp.model.Todo;
import lombok.*;

@Getter
@NoArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private boolean done;

    @Builder
    public TodoDto(final Todo entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }
}
