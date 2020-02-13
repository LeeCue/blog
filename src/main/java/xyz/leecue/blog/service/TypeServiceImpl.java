package xyz.leecue.blog.service;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xyz.leecue.blog.dao.TypeMapper;
import xyz.leecue.blog.exception.NotFoundException;
import xyz.leecue.blog.model.Type;

import java.util.Comparator;
import java.util.List;

/**
 * @author LeeCue
 * @date 2020-02-07
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type saveType(Type type) {
        if (type == null) {
            return null;
        }
        typeMapper.createType(type);
        return type;
    }

    @Override
    public Type getType(Long id) {
        if (id == null) {
            return null;
        }
        return typeMapper.findTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return typeMapper.findTypeByName(name);
    }

    @Override
    public Page<Type> listType() {
        return typeMapper.getTypes();
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.findAllType();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        List<Type> top = typeMapper.findTop();
        top.sort(Comparator.comparingInt(t -> t.getBlogs().size()));
        // 获取前 size 个数量最多的Top分类
        if (top.size() > size) {
            return top.subList(0,size -1);
        } else {
            return top;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Type updateType(Long id, Type type) {
        Type t = getType(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        typeMapper.updateType(id, type);
        return getType(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }
}
