package com.example.jwtdemo.user.repository;

import com.example.jwtdemo.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    public Optional<User> findUserByName(String username);
    public void saveUser(User user);
}
