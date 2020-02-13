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
import xyz.leecue.blog.dto.BlogQuery;
import xyz.leecue.blog.model.Blog;
import xyz.leecue.blog.model.Type;
import xyz.leecue.blog.service.BlogService;
import xyz.leecue.blog.service.TypeService;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-12
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        Model model) {
        List<Type> allType = typeService.getAllType();
        if (id == -1) {
            id = allType.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        blogQuery.setPublish(true);
        PageHelper.startPage(pageNum, 6);
        Page<Blog> blogs = blogService.listBlog(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("types", allType);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}
