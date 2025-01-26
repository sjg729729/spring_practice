package com.example.practice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 쿼리 파라미터는 기본 생성자(필수)를 통해 객체를 생성하고 setter를 통해 필드 값을 생성한다.
 * body의 경우에는 builder 패턴 적용 가능
 */

@Getter
@Setter
@NoArgsConstructor
public class HelloRequestDto {
    private String name;
    private int amount;
}
