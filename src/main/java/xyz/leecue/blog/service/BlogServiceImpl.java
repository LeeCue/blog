package xyz.leecue.blog.service;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.leecue.blog.dao.BlogMapper;
import xyz.leecue.blog.dto.BlogQuery;
import xyz.leecue.blog.exception.NotFoundException;
import xyz.leecue.blog.model.Blog;
import xyz.leecue.blog.util.MarkdownUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        if (id == null) {
            return null;
        }
        return blogMapper.findBlogById(id);
    }

    @Override
    public Page<Blog> listBlog(BlogQuery blogQuery) {
        if (blogQuery == null) {
            return null;
        }
        return blogMapper.listBlog(blogQuery);
    }

    @Override
    public Page<Blog> showBlog() {
        return blogMapper.getBlogs();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.findBlogById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在...");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        blogMapper.updateViews(id);
        return blog;
    }

    @Override
    public Page<Blog> getBlogsByTagId(Long tagId) {
        return blogMapper.getBlogsByTagId(tagId);
    }

    @Override
    public List<Blog> listBlogRecommend(Integer size) {
        return blogMapper.getBlogRecommend(size);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public Page<Blog> searchBlog(String query) {
        if (query == null) {
            return null;
        }
        return blogMapper.getBlogsAccordingToTitle(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Blog saveBlog(Blog blog, List<Long> tagIds) {
        blogMapper.createBlog(blog);
        correlateBlogAndTag(blog.getId(), tagIds);
        return blog;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Blog updateBlog(Long id, Blog blog) {
        unCorrelateBlogAndTag(id);
        Blog findBlog = blogMapper.findBlogById(id);
        if (findBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        blogMapper.updateBlog(id, blog);
        return blogMapper.findBlogById(id);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void correlateBlogAndTag(Long blogId, List<Long> tagIds) {
        for (Long tagId : tagIds) {
            blogMapper.createTag(blogId, tagId);
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void unCorrelateBlogAndTag(Long blogId) {
        blogMapper.deleteTag(blogId);
    }
}
