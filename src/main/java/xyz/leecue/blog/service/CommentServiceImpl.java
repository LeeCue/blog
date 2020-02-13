package xyz.leecue.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.leecue.blog.dao.CommentMapper;
import xyz.leecue.blog.model.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-12
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        if (blogId == null) {
            return null;
        }
        List<Comment> comments = commentMapper.findCommentByBlogId(blogId);
        return eachComment(comments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Comment saveComment(Long blogId, Comment comment) {
        Long parentId = comment.getParentComment().getId();
        commentMapper.createComment(comment);
        if (parentId != -1) {
            //如果父评论不为空的话，那么直接建立父子评论之间的关系
            comment.setParentCommentId(parentId);
            commentMapper.correlateReplyComment(parentId,comment.getId());
        } else {
            //如果父评论为空，那么建立博客与评论之间的关系
            commentMapper.createBlogComment(blogId, comment.getId());
        }
        return comment;
    }

    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>(comments);
        combineChildren(commentsView);
        return commentsView;
    }

    private List<Comment> tmpComments = new ArrayList<>();

    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply1 : replys) {
                //循环迭代，找出子代
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代后处理的集合
            comment.setReplyComments(tmpComments);
            //清除临时存放区
            tmpComments = new ArrayList<>();
        }
    }

    private void recursively(Comment comment) {
        //顶节点先放入临时集合
        tmpComments.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tmpComments.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}
