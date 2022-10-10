package com.sparta.homework2.dto;

import com.sparta.homework2.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String username;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUsername());
    }
}
