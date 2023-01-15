package com.blog.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * tag与blog的映射
 * </p>
 *
 * @author qtw
 * @since 2023-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TagBlog对象", description="tag与blog的映射")
public class TagBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tag的id")
    private Long tagId;

    @ApiModelProperty(value = "blog的id")
    private Long blogId;


}
