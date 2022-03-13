package com.example.jwtdemo.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserAuthority {
    private Long userId;
    private int  authorityId;

    public void updateUserAuthority(int authorityId) {
        this.authorityId = authorityId;
    }
}
