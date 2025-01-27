package com.example.practice.web;

import com.example.practice.service.posts.PostsService;
import com.example.practice.web.dto.PostsResponseDto;
import com.example.practice.web.dto.PostsSaveRequestDto;
import com.example.practice.web.dto.PostsUpdateRequestDto;
import com.example.practice.web.dto.SuccessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 저장
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // 수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 조회
    @GetMapping("/api/v1/posts/{id}")
    public SuccessDto<Object> findById(@PathVariable Long id) {
        PostsResponseDto post = postsService.findById(id);
        return SuccessDto.builder()
                .success(true)
                .data(post)
                .message("성공!!")
                .build();
    }
}
