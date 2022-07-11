package com.example.springboottodoapp.service;

import com.example.springboottodoapp.model.TodoEntity;
import com.example.springboottodoapp.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public String testService() {
        TodoEntity todoEntity = TodoEntity.builder().title("My First SpringBoot Todo Item").build();
        todoRepository.save(todoEntity);
        TodoEntity savedEntity = todoRepository.findById(todoEntity.getId()).get();
        return savedEntity.getTitle();
    }
}
