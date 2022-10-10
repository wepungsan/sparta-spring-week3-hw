package com.sparta.homework2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticlePasswordRequestDto {
    private String password;

    public ArticlePasswordRequestDto(String password) {
        this.password = password;
    }
}
