package com.sixkery.kike.admin.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sixkery.kike.common.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_resource_category")
@ApiModel(value = "资源对象", description = "资源表")
public class ResourceCategoryDo extends BaseDO {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "资源类型名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
