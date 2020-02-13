package xyz.leecue.blog.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.leecue.blog.model.Tag;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-08
 */
@Repository
public interface TagMapper {
    void createTag(Tag tag);

    Tag findTagById(Long id);

    Tag findTagByName(String name);

    List<Tag> findTagsByBlogId(Long blogId);

    Page<Tag> getTags();

    List<Tag> getTagTop();

    List<Tag> findAllTag();

    int updateTag(@Param("nid") Long id,
                  @Param("tag") Tag tag);

    void deleteTag(Long id);
}
