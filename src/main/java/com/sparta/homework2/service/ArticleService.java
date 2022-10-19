package com.sparta.homework2.service;

import com.sparta.homework2.dto.ArticlePasswordRequestDto;
import com.sparta.homework2.dto.ArticleRequestDto;
import com.sparta.homework2.dto.ArticleResponseDto;
import com.sparta.homework2.jwt.TokenProvider;
import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Member;
import com.sparta.homework2.repository.ArticleRepository;
import com.sparta.homework2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public List<ArticleResponseDto> getArticles() throws SQLException {
        List<ArticleResponseDto> articlesDto = articleRepository.findAll()
                .stream().map(article -> article.toDto()).collect(Collectors.toList());

        return articlesDto;
    }

    public ArticleResponseDto getArticle(Long id) throws SQLException {
        ArticleResponseDto articleDto = articleRepository.findById(id).orElse(null).toDto();
        return articleDto;
    }

    public String checkPassword(Long id, ArticlePasswordRequestDto requestDto) throws SQLException {
        Article article = articleRepository.findById(id).orElse(null);

        try {
            if(article.getPassword().equals(requestDto.getPassword())) {
                return "일치합니다.";
            } else {
                return "일치하지 않습니다.";
            }
        }
        catch (NullPointerException ex) {
            return ex.getMessage();
        }
    }

    public Article createArticle(ArticleRequestDto requestDto) throws SQLException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Article article = new Article(member.getUsername(), requestDto);

        articleRepository.save(article);

        return article;
    }

    public Long deleteArticle(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));

        if(!member.getUsername().equals(article.getAuthor())) {
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
        }

        articleRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Article update(Long id, ArticleRequestDto requestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));

        if(!member.getUsername().equals(article.getAuthor())) {
           throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        article.setAuthor(member.getUsername());
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());
        article.setPassword(requestDto.getPassword());
        articleRepository.save(article);

        return article;
    }
}
