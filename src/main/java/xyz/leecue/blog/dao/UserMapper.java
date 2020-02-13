package xyz.leecue.blog.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.leecue.blog.model.User;

/**
 * @author LeeCue
 * @date 2020-02-04
 */
@Repository
public interface UserMapper {
    User findByUsernameAndPassword(@Param("username") String username,
                                   @Param("password")String password);

    User findUserById(Long id);
}
