package com.blog.service.impl;

import com.blog.entity.Blogs;
import com.blog.mapper.BlogsMapper;
import com.blog.service.BlogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客 服务实现类
 * </p>
 *
 * @author qtw
 * @since 2023-01-07
 */
@Service
public class BlogsServiceImpl extends ServiceImpl<BlogsMapper, Blogs> implements BlogsService {

    @Resource
    private BlogsMapper blogsMapper;

    @Override
    public List<Map> getBlogs(String title, String tag, int pageSize, int pageNum) {
        return blogsMapper.getBlogs(title, tag, pageSize, pageNum);
    }
}
