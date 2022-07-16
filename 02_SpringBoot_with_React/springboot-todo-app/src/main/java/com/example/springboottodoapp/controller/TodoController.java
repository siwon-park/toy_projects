package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.ResponseDto;
import com.example.springboottodoapp.dto.TodoDto;
import com.example.springboottodoapp.model.TodoEntity;
import com.example.springboottodoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String s = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(s);
        ResponseDto<String> responseDto = ResponseDto.<String>builder().data(list).build();
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {
        try {
            String tempUserName = "temp-user";
            // todoDto를 Entity로 변환함(toEntity 메서드를 사용하지 않고 빌드패턴을 이용)
            TodoEntity todoEntity = TodoEntity.builder()
                    .id(null) // 최초 생성 당시 id는 null로 초기화
                    .username(tempUserName)
                    .title(todoDto.getTitle())
                    .done(todoDto.isDone())
                    .build();

            // Service를 이용해서 Todo엔티티를 생성함
            List<TodoEntity> todoEntities = todoService.create(todoEntity);
            
            // 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환
            List<TodoDto> todoDtos = todoEntities.stream().map(TodoDto::new).collect(Collectors.toList());
            
            // 반환된 TodoDto를 이용해 ResponseDTO를 초기화함
            ResponseDto<TodoDto> responseDto = ResponseDto.<TodoDto>builder().data(todoDtos).build();
            
            // ResponseDto를 반환
            return ResponseEntity.ok().body(responseDto);
        } catch (Exception e) {
            // 예외가 있는 경우 dto 대신 error에 메세지를 넣어서 리턴함
            String error = e.getMessage();
            ResponseDto<TodoDto> responseDto = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String tempUserName = "temp-user";

        // 서비스의 retrieve 메서드를 사용해 Todo리스트를 가져옴
        List<TodoEntity> todoEntities = todoService.retrieve(tempUserName);
        
        // 자바 스트림을 이용해 반환된 엔티티 리스트를 TodoDto리스트로 변환
        List<TodoDto> todoDtos = todoEntities.stream().map(TodoDto::new).collect(Collectors.toList());
        
        // 변환된 TodoDto 리스트를 이용해 ResponseDTO를 초기화
        ResponseDto<TodoDto> responseDto = ResponseDto.<TodoDto>builder().data(todoDtos).build();

        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDto todoDto) {
        String tempUserName = "temp-user";

        // dto를 entity로 변환
        TodoEntity todoEntity = TodoEntity.builder()
                .id(todoDto.getId())
                .title(todoDto.getTitle())
                .username(tempUserName)
                .done(todoDto.isDone())
                .build();

        // 서비스를 이용해 Entity를 업데이트
        List<TodoEntity> todoEntities = todoService.update(todoEntity);

        // 자바 스트림을 이용해 리턴된 엔티티를 TodoDTO 리스트로 변환
        List<TodoDto> todoDtos = todoEntities.stream().map(TodoDto::new).collect(Collectors.toList());

        // 변환된 TodoDto리스트를 이용해 ResponseDTO를 초기화
        ResponseDto<TodoDto> responseDto = ResponseDto.<TodoDto>builder().data(todoDtos).build();

        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDto todoDto) {
        try {
            String tempUserName = "temp-user";

            // dto를 entity로 변환
            TodoEntity todoEntity = TodoEntity.builder()
                    .id(todoDto.getId())
                    .title(todoDto.getTitle())
                    .username(tempUserName)
                    .done(todoDto.isDone())
                    .build();
            
            // 서비스를 호출하여 Entity를 삭제한다
            List<TodoEntity> todoEntities = todoService.delete(todoEntity);

            // 자바 스트림을 이용하여 리턴된 엔티티 리스트를 TodoDto 리스트로 변환
            List<TodoDto> todoDtos = todoEntities.stream().map(TodoDto::new).collect(Collectors.toList());
            
            // 변환된 TodoDto 리스트를 활용해 Response Dto를 생성한다
            ResponseDto<TodoDto> responseDto = ResponseDto.<TodoDto>builder().data(todoDtos).build();

            return ResponseEntity.ok().body(responseDto);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDto<TodoDto> responseDto = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

}
