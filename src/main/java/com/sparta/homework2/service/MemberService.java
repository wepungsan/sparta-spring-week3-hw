package com.sparta.homework2.service;

import com.sparta.homework2.dto.ArticleResponseDto;
import com.sparta.homework2.dto.CommentResponseDto;
import com.sparta.homework2.dto.MemberResponseDto;
import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Comment;
import com.sparta.homework2.model.Member;
import com.sparta.homework2.repository.ArticleRepository;
import com.sparta.homework2.repository.CommentRepository;
import com.sparta.homework2.repository.MemberRepository;
import com.sparta.homework2.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String username) {
        return memberRepository.findByUsername(username)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getMyArticles(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        List<Article> articles = articleRepository.findAllByAuthor(member.getUsername())
                .orElseThrow(() -> new RuntimeException("글이 없습니다."));

        List<ArticleResponseDto> articlesDto = articles.stream()
                .map(Article::toDto)
                .collect(Collectors.toList());

        return articlesDto;
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getMyComments(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        List<Comment> comments = commentRepository.findAllByName(member.getUsername())
                .orElseThrow(() -> new RuntimeException("댓글이 없습니다."));

        List<CommentResponseDto> commentDto = comments.stream()
                .map(Comment::toDto)
                .collect(Collectors.toList());

        return commentDto;
    }
}
