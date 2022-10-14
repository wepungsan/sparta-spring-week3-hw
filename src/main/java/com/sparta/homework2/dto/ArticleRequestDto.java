package com.sparta.homework2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleRequestDto {
    private String title;
    private String password;
    private String content;

    public ArticleRequestDto(String title, String password, String content) {
        this.title = title;
        this.password = password;
        this.content = content;
    }
}
