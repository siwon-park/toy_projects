package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.ResponseDto;
import com.example.springboottodoapp.dto.TestRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test") // TestController의 모든 메서드는 기본적으로 localhost:8080/test/... 으로 맵핑됨
public class TestController {

    @GetMapping
    public String testController() {
        return "Hello Spring!";
    }

    @GetMapping("/testGetMapping") // test/testGetMapping으로 맵핑됨
    public String testControllerWithPath() {
        return "Hello Spring With Testing Get Path Mapping";
    }

    @GetMapping("/{id}") // test/{id}로 맵핑
    public String testControllerWithPathVariables(@PathVariable(required = false) Long id) {
        return "Hello Spring ID: " + id;
    }

    @GetMapping("/testRequestParam") // test/testRequestParam?id=OOO 으로 맵핑
    public String testControllerRequestParam(@RequestParam(required = false) Long id) {
        return "Hello Spring Param: " + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestDto testRequestDto) {
        return "Hello Spring ID: " + testRequestDto.getId() + " Message: " + testRequestDto.getMessage();
    }

    @GetMapping("/testResponseDto")
    public ResponseDto<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello Spring!, I'm ResponseDTO");
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return response;
    }

}
