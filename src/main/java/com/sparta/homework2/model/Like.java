package com.sparta.homework2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mamber_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    public Like(Member member, Article article){
        this.member = member;
        this.article = article;

    }
}
