package xyz.leecue.blog.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.leecue.blog.model.Blog;
import xyz.leecue.blog.model.Tag;
import xyz.leecue.blog.service.BlogService;
import xyz.leecue.blog.service.TagService;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-12
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        Model model) {
        List<Tag> allTag = tagService.getAllTag();
        if (allTag.size() > 0) {
            if (id == -1) {
                id = allTag.get(0).getId();
            }
        }
        PageHelper.startPage(pageNum, 6);
        Page<Blog> blogs = blogService.getBlogsByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("tags", allTag);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
