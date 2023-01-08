package com.blog.mapper;

import com.blog.common.Result;
import com.blog.entity.Blogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客 Mapper 接口
 * </p>
 *
 * @author qtw
 * @since 2023-01-07
 */
@Mapper
public interface BlogsMapper extends BaseMapper<Blogs> {

    List<Map> getBlogs(@Param("searchTitle") String title,
                       @Param("searchTag") String tag,
                       @Param("pageSize") int pageSize,
                       @Param("pageNum") int pageNum);
}
