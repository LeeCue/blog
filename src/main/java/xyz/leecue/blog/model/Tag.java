package xyz.leecue.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 7960514177174222959L;
    private Long id;
    private String name;

    private List<Blog> blogs;
}
