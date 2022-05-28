package com.sparta.springapihomework.service;

import com.sparta.springapihomework.dto.SignupRequestDto;
import com.sparta.springapihomework.models.User;
import com.sparta.springapihomework.models.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String checkPassword = requestDto.getCheckPassword();

        boolean usernameCheck = Pattern.matches("^[a-zA-Z0-9]{3,15}$", username);
        if(!usernameCheck){
            throw new IllegalArgumentException("아이디 형식을 확인해주세요.");
        }

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다다");
        }

        boolean passwordCheck = Pattern.matches("^[a-zA-Z0-9]{4,15}$", password);
        if (!passwordCheck) {
            throw new IllegalArgumentException("비밀번호는 4~15자리 사이로 입력해주세요.");
        }else if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에는 아이디가 포함되어서는 안됩니다.");
        }else if (!password.equals(checkPassword)) {
            throw new IllegalArgumentException("비밀번호를 확인해주세요");
        }

        passwordEncoder.encode(password);
        User user = new User(username, password);
        userRepository.save(user);
    }
}
