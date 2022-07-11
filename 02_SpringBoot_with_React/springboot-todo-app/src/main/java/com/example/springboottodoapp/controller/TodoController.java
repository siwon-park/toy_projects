package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.ResponseDto;
import com.example.springboottodoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
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
}
