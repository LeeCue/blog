package xyz.leecue.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-04
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 3041009904131928864L;
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs;
}
