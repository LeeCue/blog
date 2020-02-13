package xyz.leecue.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.leecue.blog.dao.UserMapper;
import xyz.leecue.blog.model.User;

/**
 * @author LeeCue
 * @date 2020-02-04
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = null;

        if(username != null && password != null) {
            user = userMapper.findByUsernameAndPassword(username, password);
        }

        return user;
    }
}
