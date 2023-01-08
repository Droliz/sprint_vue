package com.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Tags对象", description="")
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tag_id;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "颜色")
    private String color;

}
