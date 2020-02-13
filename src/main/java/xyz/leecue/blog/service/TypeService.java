package xyz.leecue.blog.service;

import com.github.pagehelper.Page;
import xyz.leecue.blog.model.Type;

import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType();

    List<Type> getAllType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
