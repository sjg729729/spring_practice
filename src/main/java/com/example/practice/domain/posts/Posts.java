package com.example.practice.domain.posts;

import com.example.practice.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * jpa는 객체를 생성할 때 기본 생성자를 사용 (필수)
 * setter가 없어도 jpa는 필드에 직접 접근해 엔티티를 관리
 * 비즈니스 로직은 setter를 사용 하지 않음. 메서드를 통해 명시적으로 수정
 */

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
