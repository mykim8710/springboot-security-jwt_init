package com.example.jwtdemo.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class User {
    private Long id;
    private String username;
    private String password;
    private String authorityNames;   // JOIN Authority TABLE > MASTER,USER....

   public List<String> getAuthorityList() {
        if(this.authorityNames.length() > 0) {
            return Arrays.asList(this.authorityNames.split(","));
        }

        return new ArrayList<>();
   }

   @Builder
   public User(String username, String password) {
        this.username = username;
        this.password = password;
   }










}
