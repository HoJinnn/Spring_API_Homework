package com.sparta.springapihomework.model;

import com.sparta.springapihomework.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Setter
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String password;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.password = requestDto.getPassword();
    }
}
