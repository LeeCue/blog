package xyz.leecue.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Data
public class Type implements Serializable {
    private static final long serialVersionUID = 4526639650912127497L;
    private Long id;
    private String name;

    private List<Blog> blogs;
}
