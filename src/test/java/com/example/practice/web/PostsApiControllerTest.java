package com.example.practice.web;

import com.example.practice.domain.posts.Posts;
import com.example.practice.domain.posts.PostsRepository;
import com.example.practice.web.dto.PostsSaveRequestDto;
import com.example.practice.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PostsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void afterEach() {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions result = mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // then
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("title").value(requestDto.getTitle()))
//                .andExpect(jsonPath("content").value(requestDto.getContent()));
        result.andExpect(status().isOk());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    void posts_수정된다() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions result = mockMvc.perform(put("/api/v1/posts/" + updateId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // then
        MvcResult mvcResult = result.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();

        assertThat(Long.parseLong(responseBody)).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    void baseTimeEntity_등록() {

        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        Posts posts = all.get(0);

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
