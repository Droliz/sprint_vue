package com.blog.mapper;

import com.blog.entity.TagBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * tag与blog的映射 Mapper 接口
 * </p>
 *
 * @author qtw
 * @since 2023-01-12
 */
@Mapper
public interface TagBlogMapper extends BaseMapper<TagBlog> {

}
