package com.sparta.springapihomework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class PostResponseDto {
    private final String title;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;
}
