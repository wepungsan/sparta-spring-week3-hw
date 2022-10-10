package com.sparta.homework2.service;

import com.sparta.homework2.dto.*;
import com.sparta.homework2.jwt.TokenProvider;
import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Comment;
import com.sparta.homework2.model.Member;
import com.sparta.homework2.repository.ArticleRepository;
import com.sparta.homework2.repository.CommentRepository;
import com.sparta.homework2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public List<CommentResponseDto> getComments(Long id) throws SQLException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 없습니다."));

        List<CommentResponseDto> comments = commentRepository.findByArticle(article)
                .orElseThrow(() -> new NullPointerException("해당 글이 없습니다."))
                .stream().map(comment -> comment.toDto()).collect(Collectors.toList());

        return comments;
    }

    public Comment createComment(Long id, CommentRequestDto requestDto) throws SQLException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 없습니다."));

        Comment comment = new Comment(requestDto.getName(), requestDto.getComment(), article);

        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public Comment updateComment(ServletRequest request, CommentUpdateRequestDto requestDto) {
        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("Authorization").substring(7);

        Authentication auth = tokenProvider.getAuthentication(token);

        Long memberId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        Comment comment = commentRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NullPointerException("해당 댓글이 존재하지 않습니다."));

        if(!member.getUsername().equals(comment.getName())) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        comment.setComment(requestDto.getComment());
        comment.setName(requestDto.getName());
        commentRepository.save(comment);

        return comment;
    }

    public Long deleteComment(ServletRequest request, Long id) {
        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("Authorization").substring(7);

        Authentication auth = tokenProvider.getAuthentication(token);

        Long memberId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));

        if(!member.getUsername().equals(comment.getName())) {
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(id);
        return id;
    }
}
