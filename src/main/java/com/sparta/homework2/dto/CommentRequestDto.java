package com.sparta.homework2.dto;

import com.sparta.homework2.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentRequestDto {
    private String comment;

    public CommentRequestDto(String comment) {
        this.comment = comment;
    }
}
