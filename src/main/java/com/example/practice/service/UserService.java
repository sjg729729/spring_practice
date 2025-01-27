package com.example.practice.service;

import com.example.practice.domain.User.User;
import com.example.practice.domain.User.UserRepository;
import com.example.practice.jwt.JwtProvider;
import com.example.practice.web.dto.JoinRequestDto;
import com.example.practice.web.dto.SignInRequestDto;
import com.example.practice.web.dto.SignInResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 회원가입
     */
    public void signup(JoinRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(encodedPassword)
                .email(requestDto.getEmail())
                .build();
        userRepository.save(user);
    }

    public SignInResponseDto signin(SignInRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername());

        if (user == null || !passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습습니다.");
        }

        String token = jwtProvider.generateToken(user.getUsername());
        return new SignInResponseDto(user.getUsername(), token);

    }
}
