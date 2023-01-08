package com.qtw.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtw.common.QueryPageParma;
import com.qtw.common.Result;
import com.qtw.entity.User;
import com.qtw.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qtw
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/user")
// @CrossOrigin(origins = "*")   // 局部跨域  允许所有
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    // 新增、修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user) {
        System.out.println(user);
        return userService.saveOrUpdate(user) ? Result.success() : Result.fail();
    }

    // 删除
    @DeleteMapping ("/deleteById")
    public Result deleteById(Integer id) {
        return userService.removeById(id) ? Result.success() : Result.fail();
    }

    // 查询（模糊，匹配）
    @PostMapping("/listParam")
    public Result listParam(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());  // like 模糊匹配   eq 完全匹配
        }
        return Result.success(userService.list(lambdaQueryWrapper));
    }

    // 分页
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParma query) {

        User parma = query.getParma();
        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());
        // 自带的分页   页数、每页大小
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, parma.getName());
        IPage<User> result = userService.page(page);

        return result.getRecords();
    }

    // 分页
    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParma query) {

        User parma = query.getParma();
        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, parma.getName());

        // 自定义分页
        // IPage<User> result = userService.pageC(page);
        // 使用自定义的 wrapper
        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println(result.getTotal());

        return result.getRecords();
    }

    // 分页
    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParma query) {
//        System.out.println(query);
        User parma = query.getParma();
        System.out.println(parma);
        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (parma.getName() == null) {
            parma.setName("");
        }
        lambdaQueryWrapper.like(User::getName, parma.getName());
        if (parma.getSex() != null) {
            lambdaQueryWrapper.eq(User::getSex, parma.getSex());
        }
        // 自定义分页
        // IPage<User> result = userService.pageC(page);
        // 使用自定义的 wrapper
        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println(result.getTotal());

        // 自定义返回格式
        return Result.success(result.getRecords(), result.getTotal());
    }

    // 获得账号信息
    @GetMapping("/FindByNo")
    public Result FindByNo(@RequestParam String no) {
        List<User> res = userService.lambdaQuery().eq(User::getNo, no).list();
        return res.size() > 0 ? Result.success(res) : Result.fail();
    }

    // 登录
    @GetMapping("/login")//映射嘛
    public Result login(@RequestParam(name = "no", required = true) String no,
                        @RequestParam(name = "password", required = true) String password) {//在这里引入了参数,也就是前端传进来的参数,似乎@RequestParam可以去找传进来的参数,当然是用name进行匹配了,
        List<User> res =  userService.lambdaQuery().eq(User::getNo, no)
                .eq(User::getPassword, password).list();
        // 未查到
        if (res.isEmpty()) {
            return Result.fail("用户名或密码错误");
        }

        // 未启用
        if (Objects.equals(res.get(0).getIsvalid(), "N")) {
            return Result.fail("该账号未启用，请联系管理员");
        }

        // 登录中
        if (Objects.equals(res.get(0).getPrefsession(), 1)) {
            return Result.fail("该账号已登陆");
        }

        User temp = res.get(0);
        temp.setPrefsession(1);

        // 更改状态
        userService.updateById(temp);
        // 设置 token


        return Result.success(res, (long) res.size(), "登陆成功");
    }
}
