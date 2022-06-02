package com.sparta.springapihomework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {
    private final String author;
    private final String comment;
}
