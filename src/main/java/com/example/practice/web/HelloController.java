package com.example.practice.web;

import com.example.practice.web.dto.HelloRequestDto;
import com.example.practice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name") String name,
            @RequestParam("amount") int amount
    ) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/hello/dto/v1")
    public HelloResponseDto helloDto1(@ModelAttribute HelloRequestDto requestDto) {
        System.out.println(requestDto.getName());
        return new HelloResponseDto(requestDto.getName(), requestDto.getAmount());
    }
}
