package com.sparta.springapihomework.controller;

import com.sparta.springapihomework.models.*;
import com.sparta.springapihomework.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public GetOnePostDto getOnePost(@PathVariable Long id) {
        return postService.getOnePost(id);
    }

    @PostMapping("/api/posts")
    public PostRequestDto createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return requestDto;
    }

    @PutMapping("/api/posts/{id}")
    public PostRequestDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(id, requestDto);
        return requestDto;
    }

    @DeleteMapping("/api/posts/{id}")
    public PostDeleteDto deletePost(@PathVariable Long id, @RequestBody PostDeleteDto deleteDto) {
        postService.deletePost(id, deleteDto);
        return deleteDto;
    }
}