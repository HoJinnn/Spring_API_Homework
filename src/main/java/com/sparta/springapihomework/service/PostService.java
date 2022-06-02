package com.sparta.springapihomework.service;

import com.sparta.springapihomework.dto.GetAllPostDto;
import com.sparta.springapihomework.dto.PostDeleteDto;
import com.sparta.springapihomework.dto.PostRequestDto;
import com.sparta.springapihomework.model.Post;
import com.sparta.springapihomework.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Post> getOnePost(Long id) {
        return postRepository.findById(id);
    }
    public void deletePost(Long id, PostDeleteDto deleteDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(Objects.equals(deleteDto.getPassword(), post.getPassword()))
            postRepository.deleteById(id);
    }
}