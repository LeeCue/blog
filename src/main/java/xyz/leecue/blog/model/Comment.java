package xyz.leecue.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 4845571476443327936L;
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long parentCommentId = -1L;
    private Boolean adminComment = Boolean.FALSE;

    private Blog blog;

    private List<Comment> replyComments;

    private Comment parentComment;
}
