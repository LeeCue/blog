package xyz.leecue.blog.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.leecue.blog.model.Type;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Repository
public interface TypeMapper {
    void createType(Type type);

    Type findTypeById(Long id);

    Type findTypeByName(String name);

    List<Type> findAllType();

    Page<Type> getTypes();

    List<Type> findTop();

    int updateType(@Param("nid") Long id,
                    @Param("type") Type type);

    void deleteType(Long id);
}
