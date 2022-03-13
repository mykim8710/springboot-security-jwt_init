package com.example.jwtdemo.user.repository;

import com.example.jwtdemo.user.domain.User;
import com.example.jwtdemo.user.domain.UserAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {
    public Optional<User> findUserByUsername(String username);

    public void insertUser(User user);
    public void insertUserAuthority(UserAuthority userAuthority);
}
