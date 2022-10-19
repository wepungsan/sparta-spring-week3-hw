package com.sparta.homework2.controller;


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
    public ResponseEntity<?> createLike(@PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(likeservice.createLike(id));
    }

    @GetMapping("/api/like/{memberId}")
    public ResponseEntity<?> getArticleWithLikes(@PathVariable Long memberId)
            throws SQLException {
        try {
            return ResponseEntity.ok(likeservice.getArticleWithLikes(memberId));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(404));
        }
    }
}
