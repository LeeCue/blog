package xyz.leecue.blog.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;
import xyz.leecue.blog.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Data
public class Blog implements Serializable {
    private static final long serialVersionUID = -6310391412802510695L;
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    /** 文章类型 */
    private String flag;
    private Integer views = 0;
    private Boolean appreciationSwitch = Boolean.FALSE;
    private Boolean copyRightSwitch = Boolean.FALSE;
    private Boolean commentSwitch = Boolean.FALSE;
    private Boolean publish;
    private Boolean recommend = Boolean.FALSE;
    private Date createTime;
    private Date updateTime;
    private String description;
    private Long user_id;
    private Long type_id;

    private Type type;

    private List<Tag> tags;

    private User user;

    private List<Comment> comments;

    public String findTagIds(){
        return StringUtils.join(tags);
    }
}
