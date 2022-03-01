package com.example.jwtdemo.user.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private String roleType;

    Role(String roleType) {
        this.roleType = roleType;
    }

}
