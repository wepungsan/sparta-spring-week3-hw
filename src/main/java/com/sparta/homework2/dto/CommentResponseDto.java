package com.sparta.homework2.dto;

import com.sparta.homework2.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private String name;
    private String comment;
    private Article article;

    public CommentResponseDto(Long id, String name, String comment, Article article) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.article = article;
    }
}
