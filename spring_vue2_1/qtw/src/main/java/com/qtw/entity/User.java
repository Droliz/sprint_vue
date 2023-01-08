package com.qtw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qtw
 * @since 2022-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String no;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "角色：0超级管理员、1管理员、2普通账号")
    private Integer roleId;

    @ApiModelProperty(value = "是否有效")
    @TableField("isValid")
    private String isvalid;

    @ApiModelProperty(value = "登录状态 0 未登录 1 登录")
    private Integer prefsession;
}
