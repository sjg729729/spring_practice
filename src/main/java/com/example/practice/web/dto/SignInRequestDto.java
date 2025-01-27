package com.example.practice.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
