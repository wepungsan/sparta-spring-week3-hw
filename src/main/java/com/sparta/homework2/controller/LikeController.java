package com.sparta.homework2.controller;

import com.sparta.homework2.service.Likeservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final Likeservice likeservice;

    @PostMapping("/api/like/{id}")
    public ResponseEntity<?> createLike(@PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(likeservice.createLike(id));
    }
}
