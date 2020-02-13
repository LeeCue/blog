package xyz.leecue.blog.util;

import xyz.leecue.blog.model.Tag;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-10
 */
public class StringUtils {

    public static String join(List<Tag> tags) {
        StringBuilder builder = new StringBuilder();
        for (Tag tag : tags) {
            builder.append(tag.getId().toString());
            builder.append(",");
        }
        return builder.substring(0,builder.length() - 1);
    }

}
