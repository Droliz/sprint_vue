package com.blog.service.impl;

import com.blog.entity.TagBlog;
import com.blog.mapper.TagBlogMapper;
import com.blog.service.TagBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * tag与blog的映射 服务实现类
 * </p>
 *
 * @author qtw
 * @since 2023-01-12
 */
@Service
public class TagBlogServiceImpl extends ServiceImpl<TagBlogMapper, TagBlog> implements TagBlogService {

}
