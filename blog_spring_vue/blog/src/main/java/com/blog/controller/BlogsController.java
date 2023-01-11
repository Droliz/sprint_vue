package com.blog.controller;


import com.blog.common.Parma;
import com.blog.common.QueryPageParma;
import com.blog.common.Result;
import com.blog.service.BlogsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    // 获取所有博客信息（多表查询）
    @PostMapping("/getBlogs")
    public Result getBlogs(@RequestBody QueryPageParma query) {

        Parma parma = query.getParma();
        if (parma == null) {
            parma = new Parma();
        }
        if (Objects.equals(parma.getTitle(), "")) {
            parma.setTitle("%");
        } else {
            parma.setTitle("%" + parma.getTitle() + "%");
        }
        if (Objects.equals(parma.getTag(), "")) {
            parma.setTag("%");
        }
//        System.out.println(parma);
        List<Map> res = blogsService.getBlogs(
                parma.getTitle(),
                parma.getTag(),
                query.getPageSize(),
                (query.getPageNum()-1) * query.getPageSize());

        return Result.success(res, getBlogCount(), (long) res.size());
    }

    // 根据 ID 获取文章详情
    @GetMapping("getBlogById")
    public Result getBlogById(@RequestParam Long id) {

        List<Map> res = blogsService.getBlogById(id);

        if (res.size() == 0) {
            return Result.fail("没有找到该博客，请联系管理员");
        }

        return Result.success(res, getBlogCount(), 1L);
    }

    // 获取所有列表 按时间分
    @GetMapping("getBlogsTime")
    public Result getBlogsTime(@RequestParam String format) {
        System.out.println(format);
        List<Map> res = blogsService.getBlogsTime(format);
        System.out.println(res);
        return Result.success(res, getBlogCount(), (long) res.size());
    }
}
