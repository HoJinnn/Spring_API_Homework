package com.sparta.springapihomework.controller;

import com.sparta.springapihomework.dto.CommentModifyDto;
import com.sparta.springapihomework.dto.CommentRequestDto;
import com.sparta.springapihomework.dto.CommentResponseDto;
import com.sparta.springapihomework.model.Comment;
import com.sparta.springapihomework.model.Post;
import com.sparta.springapihomework.service.CommentService;
import com.sparta.springapihomework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("/api/posts/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long id) {
        List<CommentResponseDto> responses = commentService.getAllComment(id);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping("/api/posts/{id}/comments")
    public ResponseEntity.BodyBuilder createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        if(requestDto.getComment().equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        Post post = postService.getOnePost(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );

    Comment comment = Comment.builder()
                .author(requestDto.getAuthor())
                .comment(requestDto.getComment())
                .post(post)
                .build();

        CommentService.write(comment);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }

    @Transactional
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity.BodyBuilder modifyComment(@PathVariable Long id, @RequestBody CommentModifyDto modifyDto) {
        Comment comment = commentService.getOneComment(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        comment.modify(modifyDto.getComment());
        return ResponseEntity.status(HttpStatus.OK);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity.BodyBuilder deleteComment(@PathVariable Long id) {
        Comment comment = CommentService.getOneComment(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK);
    }

}
