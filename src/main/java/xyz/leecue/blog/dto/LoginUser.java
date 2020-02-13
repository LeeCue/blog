package xyz.leecue.blog.dto;

import lombok.Data;

/**
 * @author LeeCue
 * @date 2020-02-05
 */
@Data
public class LoginUser {
    private String username;
    private String password;
    private Boolean rememberMe;
}
