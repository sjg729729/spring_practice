package com.example.practice.web;

import com.example.practice.service.UserService;
import com.example.practice.web.dto.JoinRequestDto;
import com.example.practice.web.dto.SignInRequestDto;
import com.example.practice.web.dto.SignInResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody JoinRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signin(@Valid @RequestBody SignInRequestDto requestDto) {
        SignInResponseDto responseDto = userService.signin(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
