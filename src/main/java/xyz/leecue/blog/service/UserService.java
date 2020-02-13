package xyz.leecue.blog.service;

import xyz.leecue.blog.model.User;

/**
 * @author LeeCue
 * @date 2020-02-04
 */
public interface UserService {
    User checkUser(String username, String password);
}