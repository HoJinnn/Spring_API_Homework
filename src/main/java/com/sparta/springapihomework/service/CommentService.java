package com.sparta.springapihomework.service;

import com.sparta.springapihomework.dto.CommentResponseDto;
import com.sparta.springapihomework.model.Comment;
import com.sparta.springapihomework.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentResponseDto> getAllComment(Long id) {
        List<Comment> comments = commentRepository.findAllByPost_IdOrderByCreatedAtDesc(id);
        List<CommentResponseDto> responseDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDto comment1 = new CommentResponseDto(comment.getAuthor(), comment.getComment());
            responseDtos.add(comment1);
        }
        return responseDtos;
    }

    public void write(Comment comment) {
        commentRepository.save(comment);
    }

    public Optional<Comment> getOneComment(Long id) {
        return commentRepository.findById(id);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


}
