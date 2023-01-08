package com.blog.controller;


import com.blog.common.Result;
import com.blog.entity.Tags;
import com.blog.service.TagsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qtw
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Resource
    private TagsService tagsService;

    // 删除
    @GetMapping("/getTags")
    public Result func() {
        return Result.success(tagsService.list());
    }
}
