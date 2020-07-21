package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 24345 on 2020/6/17.
 */

@Repository
public interface UserMapper {
    List<User> getAll();
    User getOne(Integer id);
    void insert(User user);
    void update(User user);
    void delete(Integer id);
}
