package com.kcc.restful;

import com.kcc.restful.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    // static 3 members
    private static int usersCount = 3;
    static {
        users.add(new User(1, "kim", new Date(), "pass1", "001010-1111111", null));
        users.add(new User(2, "yu", new Date(), "pass2", "001010-22222222", null));
        users.add(new User(3, "lee", new Date(), "pass3", "001010-33333333", null));
    }

    // return ALL users
    public static List<User> findAll() {
        return users;
    }

    // create new User
    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }

        if(user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);
        return user;
    }

    // find One user
    public User findById(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // delete user
    public User deleteById(int id) {
        for(User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }
}
