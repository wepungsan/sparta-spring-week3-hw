package com.sparta.homework2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleResponseDto {
    private String title;
    private String author;
    private String content;

    public ArticleResponseDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
