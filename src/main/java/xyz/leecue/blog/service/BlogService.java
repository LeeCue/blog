package xyz.leecue.blog.service;

import com.github.pagehelper.Page;
import xyz.leecue.blog.dto.BlogQuery;
import xyz.leecue.blog.model.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(BlogQuery blogQuery);

    Page<Blog> showBlog();

    Blog getAndConvert(Long id);

    Page<Blog> getBlogsByTagId(Long tagId);

    List<Blog> listBlogRecommend(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    /**
     * 目前只支持根据博客标题搜索(模糊查询)
     * @param query 查询字段
     * @return 返回的博客列表
     */
    Page<Blog> searchBlog(String query);

    Blog saveBlog(Blog blog, List<Long> tagIds);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    void correlateBlogAndTag(Long blogId, List<Long> tagIds);
}
