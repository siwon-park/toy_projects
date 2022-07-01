package com.example.springboottodoapp.dto;

import lombok.*;

import java.util.List;

@Getter
public class ResponseDto<T> {
    private String error;
    private List<T> data;
}
