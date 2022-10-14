package com.sparta.homework2.controller;

import com.sparta.homework2.dto.ArticleResponseDto;
import com.sparta.homework2.dto.CommentResponseDto;
import com.sparta.homework2.dto.MemberResponseDto;
import com.sparta.homework2.model.Article;
import com.sparta.homework2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<?> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getMemberInfo(@PathVariable String username) {
        return ResponseEntity.ok(memberService.getMemberInfo(username));
    }

    @GetMapping("/{username}/article")
    public ResponseEntity<?> getMyArticles(@PathVariable String username) {
        List<ArticleResponseDto> articles = memberService.getMyArticles(username);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{username}/comments")
    public ResponseEntity<?> getMyComments(@PathVariable String username) {
        List<CommentResponseDto> comments = memberService.getMyComments(username);
        return ResponseEntity.ok(comments);
    }
}
