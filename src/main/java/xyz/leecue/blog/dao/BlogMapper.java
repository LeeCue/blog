package xyz.leecue.blog.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.leecue.blog.dto.BlogQuery;
import xyz.leecue.blog.model.Blog;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
@Repository
public interface BlogMapper {

    void createBlog(Blog blog);

    void createTag(@Param("blogId") Long blogId,
                   @Param("tagId") Long tagId);

    void deleteTag(Long blogId);

    Page<Blog> listBlog(BlogQuery blogQuery);

    Page<Blog> getBlogs();

    Page<Blog> getBlogsAccordingToTitle(String query);

    Page<Blog> getBlogsByTagId(Long tagId);

    List<Blog> getBlogRecommend(Integer size);

    List<String> findGroupYear();

    List<Blog> findByYear(String year);

    Blog findBlogById(Long id);

    Long countBlog();

    void updateBlog(@Param("blogId") Long id,
                    @Param("blog") Blog blog);

    void updateViews(Long id);

    void deleteBlog(Long id);
}
