package com.sparta.homework2.dto;

import com.sparta.homework2.model.Authority;
import com.sparta.homework2.model.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    @Pattern(regexp="([a-z]|[A-Z]|[0-9]){4,12}",
            message = "비밀번호는 알파벳 대소문자와 숫자 4자~12자의 Username 이어야 합니다.")
    private String username;
    @Pattern(regexp="([a-z]|[0-9]){4,32}",
            message = "비밀번호는 알파벳 대소문자와 숫자 6자~32자의 Password 이어야 합니다.")
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
