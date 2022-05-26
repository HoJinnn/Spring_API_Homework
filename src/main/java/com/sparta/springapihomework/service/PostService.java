package com.sparta.springapihomework.service;

import com.sparta.springapihomework.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostRequestDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(Objects.equals(requestDto.getPassword(), post.getPassword()))
            post.update(requestDto);
        return requestDto;
    }

    public List<GetAllPostDto> getAllPost() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<GetAllPostDto> getAllPostDtos = new ArrayList<>();

        for (Post post : posts) {
            GetAllPostDto getAllPostDto = new GetAllPostDto(post.getTitle(), post.getName(), post.getCreatedAt());
            getAllPostDtos.add(getAllPostDto);
        }
        return getAllPostDtos;
    }

    public GetOnePostDto getOnePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new GetOnePostDto(post.getName(), post.getTitle(), post.getDescription(), post.getCreatedAt());
    }

    public void deletePost(Long id, PostDeleteDto deleteDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(Objects.equals(deleteDto.getPassword(), post.getPassword()))
            postRepository.deleteById(id);
    }
}