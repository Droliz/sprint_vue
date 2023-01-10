package com.blog.service;

import com.blog.common.Result;
import com.blog.entity.Blogs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客 服务类
 * </p>
 *
 * @author qtw
 * @since 2023-01-07
 */
public interface BlogsService extends IService<Blogs> {

    List<Map> getBlogs(String title, String tag, int pageSize, int pageNum);

    List<Map> getBlogById(int id);

    List<Map> getBlogsTime(String format);
}
