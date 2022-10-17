package com.sparta.homework2.repository;

import com.sparta.homework2.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Like, Long> {
}