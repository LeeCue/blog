package xyz.leecue.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.leecue.blog.model.Type;
import xyz.leecue.blog.service.TypeService;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;


    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        Model model) {
        PageHelper.startPage(pageNum, 5);
        Page<Type> types = typeService.listType();

        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo", pageInfo);
        //System.out.println(pageInfo);
        return "/admin/types";
    }

    @GetMapping("/types/input")
    public String Input() {
        return "/admin/types-input";
    }

    @GetMapping("/types/{id}/inputType")
    public String editInput(@PathVariable Long id, Model model) {
        if (id != null) {
            model.addAttribute("type",typeService.getType(id));
        }
        return "/admin/types-input";
    }

    @PostMapping("/typesSave")
    public String post(Model model, Type type, RedirectAttributes attributes) {
        Type saveType = null;
        if (typeService.getTypeByName(type.getName()) == null) {
            saveType = typeService.saveType(type);
        } else {
            model.addAttribute("message", "该分类已经存在");
            return "/admin/types-input";
        }
        if (saveType == null) {
            //没有保存成功
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            //保存成功
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/update")
    public String editPost(Model model,@PathVariable Long id,Type type, RedirectAttributes attributes) {
        Type updateType = typeService.getType(id);
        model.addAttribute("type",type);
        return "/admin/types-update";
    }

    @PostMapping("/types/update")
    public String editUpdate(Model model, Long id, Type type,RedirectAttributes attributes) {
        Type updateType = null;
        if (typeService.getTypeByName(type.getName()) == null) {
            updateType = typeService.updateType(id, type);
        } else {
            model.addAttribute("message", "该分类已经存在");
            return "/admin/types-update";
        }
        if (updateType == null) {
            //没有修改成功
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            //修改成功
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
