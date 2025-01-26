package com.example.practice.dto;

import com.example.practice.web.dto.HelloResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloResponseDtoTest {

    @Test
    void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
        Assertions.assertThat(dto.getName()).isEqualTo(name);
    }
}
