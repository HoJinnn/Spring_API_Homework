package com.sparta.springapihomework.controller;

import com.sparta.springapihomework.dto.*;
import com.sparta.springapihomework.model.*;
import com.sparta.springapihomework.repository.PostRepository;
import com.sparta.springapihomework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/posts")
    public List<GetAllPostDto> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity<PostResponseDto> getOnePost(@PathVariable Long id) {
        Post post;
        try {
            post = postService.getOnePost(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
        PostResponseDto responseDto = PostResponseDto.builder()
                .title(post.getTitle())
                .name(post.getName())
                .description(post.getDescription())
                .createdAt(post.getCreatedAt())
                .build();
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/api/posts")
    public PostRequestDto createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return requestDto;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostDeleteDto deleteDto) {
        postService.deletePost(id, deleteDto);
        return id;
    }
}
