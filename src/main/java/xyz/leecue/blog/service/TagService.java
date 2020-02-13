package xyz.leecue.blog.service;

import com.github.pagehelper.Page;
import xyz.leecue.blog.model.Tag;
import xyz.leecue.blog.model.Type;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    Page<Tag> listTag();

    List<Tag> listTags(Integer size);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
