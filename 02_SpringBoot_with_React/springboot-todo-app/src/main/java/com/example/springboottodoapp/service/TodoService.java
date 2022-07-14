package com.example.springboottodoapp.service;

import com.example.springboottodoapp.model.TodoEntity;
import com.example.springboottodoapp.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j // 로그 어노테이션
public class TodoService {

    private final TodoRepository todoRepository;

    public String testService() {
        TodoEntity todoEntity = TodoEntity.builder().title("My First SpringBoot Todo Item").build();
        todoRepository.save(todoEntity);
        TodoEntity savedEntity = todoRepository.findById(todoEntity.getId()).get();
        return savedEntity.getTitle();
    }

    // 검증 메서드
    private void validate(final TodoEntity todoEntity) {
        if (todoEntity == null) {
            log.warn("Entity Can't be null");
            throw new RuntimeException("Entity Can't be null");
        }

        if (todoEntity.getUsername() == null) {
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }
    }
    
    public List<TodoEntity> create(final TodoEntity todoEntity) { // Todo를 생성하는 메서드
        // 밸리데이션
        validate(todoEntity);

        todoRepository.save(todoEntity);
        log.info("Entity Id: {} is saved", todoEntity.getId());
        return todoRepository.findByUsername(todoEntity.getUsername());
    }

    public List<TodoEntity> retrieve(final String username) { // 검색을 위한 retrieve 메서드
        return todoRepository.findByUsername(username);
    }

    public List<TodoEntity> update(final TodoEntity todoEntity) {
        // 업데이트할 엔티티가 유효한지 확인
        validate(todoEntity);
        
        // 넘겨받은 엔티티의 아이디를 이용해서 기존의 엔티티를 가져옴(존재하지 않는 엔티티는 업데이트할 수 없기 때문)
        final Optional<TodoEntity> originalEntity = todoRepository.findById(todoEntity.getId());

        // 엔티티가 있으면 업데이트
        originalEntity.ifPresent(todo -> {
            todo.update(todoEntity.getTitle(), todoEntity.getUsername(), todoEntity.isDone());
            todoRepository.save(todo);
        });

        return retrieve(todoEntity.getUsername());
    }

    public List<TodoEntity> delete(final TodoEntity todoEntity) {
        // 삭제할 엔티티가 유효한지 확인
        validate(todoEntity);

        try {
            todoRepository.delete(todoEntity);
        } catch(Exception e) {
            log.error("Error Deleting Entity: " + todoEntity.getId(), e);
            // 데이터베이스 내부 로직을 캡슐화하기 위해 e를 반환하지 않고 새로운 Exceptin 오브젝트를 반환한다
            throw new RuntimeException("Error Deleting Entity " + todoEntity.getId());
        }

        return retrieve(todoEntity.getUsername());
    }
}
