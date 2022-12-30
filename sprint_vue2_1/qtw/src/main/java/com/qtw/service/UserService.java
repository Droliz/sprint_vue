package com.qtw.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qtw.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qtw
 * @since 2022-12-22
 */
public interface UserService extends IService<User> {

    IPage<User> pageC(IPage<User> page);

    IPage<User> pageCC(IPage<User> page, Wrapper wrapper);
}
