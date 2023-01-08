package com.blog.service.impl;

import com.blog.entity.Tags;
import com.blog.mapper.TagsMapper;
import com.blog.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qtw
 * @since 2023-01-07
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

}
