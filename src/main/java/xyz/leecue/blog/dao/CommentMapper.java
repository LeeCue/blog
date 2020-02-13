package xyz.leecue.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.leecue.blog.model.Comment;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-09
 */
@Repository
public interface CommentMapper {

    List<Comment> findChildCommentById(Long parentId);

    List<Comment> findCommentByBlogId(Long blogId);

    Comment findParentComment(Long commentId);

    Comment findOne(Long commentId);

    void correlateReplyComment(@Param("parentId") Long parentId,
                               @Param("childId") Long childId);

    void createBlogComment(@Param("blogId") Long blogId,
                           @Param("commentId") Long commentId);

    void createComment(Comment comment);
}
