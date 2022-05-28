package com.sparta.springapihomework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetOnePostDto {
    private final String name;
    private final String title;
    private final String description;
    private final LocalDateTime createdAt;
}
