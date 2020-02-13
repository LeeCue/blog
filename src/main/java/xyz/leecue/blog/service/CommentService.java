package xyz.leecue.blog.service;

import xyz.leecue.blog.model.Comment;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-12
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Long blogId, Comment comment);
}
