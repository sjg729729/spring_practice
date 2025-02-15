package com.example.practice.service.posts;

import com.example.practice.domain.posts.Posts;
import com.example.practice.domain.posts.PostsRepository;
import com.example.practice.web.dto.PostsResponseDto;
import com.example.practice.web.dto.PostsSaveRequestDto;
import com.example.practice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }
}