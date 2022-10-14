package com.sparta.homework2.repository;

import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByModifiedAtDesc();
    Optional<Article> findByAuthor(String username);
    Optional<List<Article>> findAllByAuthor(String username);
}
