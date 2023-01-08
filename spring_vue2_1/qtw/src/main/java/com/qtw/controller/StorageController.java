package com.qtw.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtw.common.QueryPageParma;
import com.qtw.common.Result;
import com.qtw.entity.Storage;
import com.qtw.entity.User;
import com.qtw.service.StorageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qtw
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    // 新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.success(storage) : Result.fail();
    }

    // 修改
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.success(storage) : Result.fail();
    }

    // 删除
    @GetMapping("/delete")
    public Result delete(@RequestParam Long id) {
        return storageService.removeById(id) ? Result.success() : Result.fail();
    }
}
