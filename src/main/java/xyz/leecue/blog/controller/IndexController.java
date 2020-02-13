package xyz.leecue.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.leecue.blog.model.Blog;
import xyz.leecue.blog.model.Tag;
import xyz.leecue.blog.model.Type;
import xyz.leecue.blog.service.BlogService;
import xyz.leecue.blog.service.TagService;
import xyz.leecue.blog.service.TypeService;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-04
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        //获取首页博客信息
        //限制数据条数可以放在application.properties中
        PageHelper.startPage(pageNum,6);
        Page<Blog> blogs = blogService.showBlog();
        PageInfo<Blog> finds = new PageInfo<>(blogs);
        List<Type> typeTop = typeService.listTypeTop(6);
        List<Tag> tagTop = tagService.listTags(8);
        List<Blog> blogRecommend = blogService.listBlogRecommend(6);
        //将查询到的数据放入模型中
        model.addAttribute("pageInfo", finds);
        model.addAttribute("typeTop",typeTop);
        model.addAttribute("tagTop", tagTop);
        model.addAttribute("blogsRecommend", blogRecommend);

        return "index";
    }

    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog", blog);
        //model.addAttribute("comments", blog.getComments());
        return "blog";
    }

    @GetMapping("/test")
    @ResponseBody
    public JSONObject test() {
        JSONObject jsonObject = new JSONObject();
        PageHelper.startPage(0,6);
        Page<Blog> blogs = blogService.showBlog();
        PageInfo<Blog> finds = new PageInfo<>(blogs);
        jsonObject.put("首页展示的blog信息", finds);
        return jsonObject;
    }


    @GetMapping("/footer/newBlog")
    public String newBlog(Model model) {
        model.addAttribute("newBlogs", blogService.listBlogRecommend(3));
        return "_fragments :: newblogList";
    }


    /**
     * 目前只支持根据博客标题(模糊)搜索
     * @param model
     * @param pageNum
     * @param query
     * @return
     */
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 6);
        Page<Blog> blogs = blogService.searchBlog(query);
        PageInfo<Blog> finds = new PageInfo<>(blogs);

        model.addAttribute("pageInfo", finds);

        return "search";
    }

}
