package xyz.leecue.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.leecue.blog.dto.BlogQuery;
import xyz.leecue.blog.model.Blog;
import xyz.leecue.blog.model.User;
import xyz.leecue.blog.service.BlogService;
import xyz.leecue.blog.service.TagService;
import xyz.leecue.blog.service.TypeService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String list(@RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum,
                           BlogQuery blogQuery, Model model) {
        model.addAttribute("types", typeService.listType());
        PageHelper.startPage(pageNum,3);
        Page<Blog> blogs = blogService.listBlog(blogQuery);
        PageInfo<Blog> finds = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",finds);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum,
                       BlogQuery blogQuery, Model model) {
        model.addAttribute("types", typeService.listType());
        PageHelper.startPage(pageNum,3);
        Page<Blog> blogs = blogService.listBlog(blogQuery);
        PageInfo<Blog> finds = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",finds);
        return "admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs/create")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes,
                       @RequestParam("tagIds")List<Long> tagIds) {
        User user = (User) session.getAttribute("user");
        blog.setUser_id(user.getId());
        Blog saveBlog = blogService.saveBlog(blog, tagIds);
        if (saveBlog != null) {
            attributes.addFlashAttribute("message", "新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/update")
    public String updateBlog(@PathVariable("id")Long blogId, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlog(blogId);
        //System.out.println(blog.getTagIds());
        model.addAttribute("blog", blog);
        return "admin/blogs-update";
    }

    @PostMapping("/blogs/updatePost")
    public String updatePost(Blog blog, RedirectAttributes attributes,HttpSession session,
                             @RequestParam("tagIds")List<Long> tagIds) {
        blog.setUser_id(((User) session.getAttribute("user")).getId());
        Blog updateBlog = blogService.updateBlog(blog.getId(), blog);
        blogService.correlateBlogAndTag(blog.getId(), tagIds);
        if (updateBlog != null) {
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            attributes.addFlashAttribute("message", "修改失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable("id")Long blogId, RedirectAttributes attributes) {
        blogService.deleteBlog(blogId);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

}
