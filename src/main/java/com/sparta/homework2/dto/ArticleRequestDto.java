package com.sparta.homework2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;

    public ArticleRequestDto(String title, String author, String password, String content) {
        this.title = title;
        this.author = author;
        this.password = password;
        this.content = content;
    }
}
