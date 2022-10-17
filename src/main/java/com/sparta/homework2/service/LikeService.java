package com.sparta.homework2.service;

import com.sparta.homework2.dto.ArticleResponseDto;
import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Like;
import com.sparta.homework2.model.Member;
import com.sparta.homework2.repository.ArticleRepository;
import com.sparta.homework2.repository.LikeRepository;
import com.sparta.homework2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Like createLike(Long id) throws SQLException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new RuntimeException("좋아요는 한번만 가능합니다.");
        }
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 없습니다."));
        Like like = new Like(member, article);
        return likeRepository.save(like);
    }

    public ArticleResponseDto getArticleWithLike(Long id) throws SQLException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getName());

        Member member = memberRepository.findById(authId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 글이 없습니다."));

        Like like = new Like(member,article);

        ArticleResponseDto articleWithLikeDto = articleRepository.findById(like.getMember().getId()).orElse(null).toDto();
        return articleWithLikeDto;
    }
}
