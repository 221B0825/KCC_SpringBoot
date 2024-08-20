package com.kcc.restful.service;

import com.kcc.restful.bean.Post;
import com.kcc.restful.bean.User;
import com.kcc.restful.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.findAllUsers();
    }

    public User findById(int id){
        return userMapper.findUser(id);
    }

    public User save(User user){
        userMapper.createUser(user);
        return user;
    }

    public void savePost(Post post){
        userMapper.createPost(post);
    }
}
