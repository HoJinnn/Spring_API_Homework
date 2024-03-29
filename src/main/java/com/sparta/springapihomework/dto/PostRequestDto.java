package com.sparta.springapihomework.dto;

import com.sparta.springapihomework.model.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private final String name;
    private final String title;
    private final String description;
    private final String password;
    private final Comment comment;
}