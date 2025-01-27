package com.example.practice.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessDto<T> {

    private boolean success;
    private T data;
    private String message;
}
