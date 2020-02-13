package xyz.leecue.blog.dto;

import lombok.Data;

/**
 * @author LeeCue
 * @date 2020-02-09
 */
@Data
public class BlogQuery {
    private String title;
    private Long typeId;
    private String flag;
    private Boolean recommend;
    private Boolean publish;
}
