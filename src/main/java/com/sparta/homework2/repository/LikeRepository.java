package com.sparta.homework2.repository;

import com.sparta.homework2.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
//    Optional<Like> findByMemberIdAndAndArticleId(Long memberId, Long articleId);
    boolean existsByMemberIdAndArticleId(Long memberId, Long articleId);
    List<Like> findAllByMemberId(Long id);
}

