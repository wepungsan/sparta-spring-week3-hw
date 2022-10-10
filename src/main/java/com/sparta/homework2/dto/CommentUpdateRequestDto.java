package com.sparta.homework2.dto;

import com.sparta.homework2.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentUpdateRequestDto {
    private Long id;
    private String name;
    private String comment;

    public CommentUpdateRequestDto(Long id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }
}
