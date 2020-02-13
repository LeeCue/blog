package xyz.leecue.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.leecue.blog.model.Tag;
import xyz.leecue.blog.service.TagService;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        Model model) {
        PageHelper.startPage(pageNum, 5);
        Page<Tag> tags = tagService.listTag();

        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo", pageInfo);
        //System.out.println(pageInfo);
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input() {
        return "/admin/tags-input";
    }

    @GetMapping("/tags/{id}/inputTag")
    public String editInput(@PathVariable Long id, Model model) {
        if (id != null) {
            model.addAttribute("tag",tagService.getTag(id));
        }
        return "/admin/tags-input";
    }

    @PostMapping("/tagsSave")
    public String post(Model model, Tag tag, RedirectAttributes attributes) {
        Tag saveTag = null;
        if (tagService.getTagByName(tag.getName()) == null) {
            saveTag = tagService.saveTag(tag);
        } else {
            model.addAttribute("message", "该标签已经存在");
            return "/admin/tags-input";
        }
        if (saveTag == null) {
            //没有保存成功
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            //保存成功
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/update")
    public String editPost(Model model,@PathVariable Long id,Tag tag) {
        Tag updateTag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "/admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String editUpdate(Model model, Long id, Tag tag,RedirectAttributes attributes) {
        Tag updateTag = null;
        if (tagService.getTagByName(tag.getName()) == null) {
            updateTag = tagService.updateTag(id, tag);
        } else {
            model.addAttribute("message", "该标签已经存在");
            return "/admin/tags-update";
        }
        if (updateTag == null) {
            //没有修改成功
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            //修改成功
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
