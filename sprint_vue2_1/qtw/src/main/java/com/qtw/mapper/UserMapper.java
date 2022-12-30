package com.qtw.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qtw.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qtw
 * @since 2022-12-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> pageC(IPage<User> page);

    // 通过注解的形式
    IPage<User> pageCC(IPage<User> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
