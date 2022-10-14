package com.sparta.homework2.repository;

import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();
    Optional<List<Comment>> findAllByArticle(Article article);
    Optional<List<Comment>> findAllByName(String username);

}
