package xyz.leecue.blog.service;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xyz.leecue.blog.dao.TagMapper;
import xyz.leecue.blog.exception.NotFoundException;
import xyz.leecue.blog.model.Tag;

import java.util.Comparator;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag saveTag(Tag tag) {
        if (tag == null) {
            return null;
        }
        tagMapper.createTag(tag);
        return tag;
    }

    @Override
    public Tag getTag(Long id) {
        if (id == null) {
            return null;
        }
        return tagMapper.findTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return tagMapper.findTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.findAllTag();
    }

    @Override
    public Page<Tag> listTag() {
        return tagMapper.getTags();
    }

    @Override
    public List<Tag> listTags(Integer size) {
        List<Tag> top = tagMapper.getTagTop();
        top.sort(Comparator.comparingInt((Tag t) -> t.getBlogs().size()));
        if(top.size() > size) {
            return top.subList(0,size-1);
        } else {
            return top;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Tag updateTag(Long id, Tag tag) {
        Tag t = getTag(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        tagMapper.updateTag(id, tag);
        return getTag(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }
}
