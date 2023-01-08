package com.blog.controller;


import com.blog.common.Parma;
import com.blog.common.QueryPageParma;
import com.blog.common.Result;
import com.blog.service.BlogsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客 前端控制器
 * </p>
 *
 * @author qtw
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @Resource
    private BlogsService blogsService;

    // 获取文章总数
    private Long getBlogCount() {
        return (long) blogsService.list().size();
    }

    // 获取博客信息（多表查询）
    @PostMapping("/getBlogs")
    public Result getBlogs(@RequestBody QueryPageParma query) {

        System.out.println(query);
        Parma parma = query.getParma();
        if (parma == null) {
            parma = new Parma();
        }

        List<Map> res = blogsService.getBlogs(
                parma.getTitle(),
                parma.getTag(),
                query.getPageSize(),
                (query.getPageNum()-1) * query.getPageSize());

        return Result.success(res, getBlogCount(), (long) res.size());
    }
}
