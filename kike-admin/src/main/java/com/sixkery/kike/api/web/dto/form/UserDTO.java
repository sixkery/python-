package com.sixkery.kike.api.web.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sixkery
 * @date 2020/12/7
 */
@Data
public class UserDTO {

    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "用户名")
    private String password;

    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "状态 0锁定 1有效")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "性别 0男 1女 2保密")
    private Integer sex;


    @ApiModelProperty(value = "0:超级管理员，1：系统用户")
    private Integer type;

    private LocalDateTime birth;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

}
