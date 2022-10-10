package com.sparta.homework2.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private Authority authority;

    @Builder
    public Member(String username, String password, Authority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
}
