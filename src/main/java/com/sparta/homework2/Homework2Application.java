package com.sparta.homework2;

import com.sparta.homework2.model.Article;
import com.sparta.homework2.model.Comment;
import com.sparta.homework2.repository.ArticleRepository;
import com.sparta.homework2.repository.CommentRepository;
import com.sparta.homework2.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Homework2Application {
	public static void main(String[] args) {
		SpringApplication.run(Homework2Application.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(ArticleRepository articleRepository, CommentRepository commentRepository) {
//		return (args) -> {
//			articleRepository.save(new Article("제목01", "이도운", "1111", "내용01"));
//			articleRepository.save(new Article("제목02", "이도희", "2222", "내용02"));
//			articleRepository.save(new Article("제목03", "이도영", "3333", "내용03"));
//
//			System.out.println("데이터 인쇄");
//			List<Article> articleList = articleRepository.findAll();
//			for (int i=0; i<articleList.size(); i++) {
//				Article article = articleList.get(i);
//				System.out.println(article.getId());
//				System.out.println(article.getTitle());
//				System.out.println(article.getAuthor());
//				System.out.println(article.getPassword());
//				System.out.println(article.getContent());
//			}
//
//			Article article1 = articleRepository.findById(1L)
//					.orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다." +
//							""));
//			commentRepository.save(new Comment("이도운", "1글 1코멘트", article1));
//			commentRepository.save(new Comment("이도운", "1글 2코멘트", article1));
//			commentRepository.save(new Comment("이도운", "1글 3코멘트", article1));
//		};
//	}
}
