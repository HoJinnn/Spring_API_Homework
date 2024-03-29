package com.sparta.springapihomework.repository;

import com.sparta.springapihomework.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByIdOrderByCreatedAtDesc(Long id);
     List<Comment> findAllByPost_IdOrderByCreatedAtDesc(Long POST_ID);
}
