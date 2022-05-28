package com.sparta.springapihomework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetAllPostDto {
    private final String name;
    private final String title;
    private final LocalDateTime createdAt;

}
