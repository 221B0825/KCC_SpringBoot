package com.kcc.restful.mapper;

import com.kcc.restful.bean.Post;
import com.kcc.restful.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAllUsers();
    public User findUser(int id);
    public void createUser(User user);
    public void createPost(Post post);
}
