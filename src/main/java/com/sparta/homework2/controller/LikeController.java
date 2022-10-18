package com.sparta.homework2.controller;

import com.sparta.homework2.dto.ArticleResponseDto;
import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Like;
import com.sparta.homework2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeservice;

    @PostMapping("/api/like/{id}")
    public ResponseEntity<Like> createLike(@PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(likeservice.createLike(id));
    }

    @GetMapping("/api/like/{id}")
    public ResponseEntity<?> getArticleWithLikes(@PathVariable Long id)
            throws SQLException {
        try {
            return ResponseEntity.ok(likeservice.getArticleWithLikes(id));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(404));
        }
    }
}
