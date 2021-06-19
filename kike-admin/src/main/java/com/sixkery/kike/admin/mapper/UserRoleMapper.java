package com.sixkery.kike.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixkery.kike.admin.entity.system.UserRoleDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDo> {

    /**
     * 批量新增
     *
     * @param userRoleDos 用户角色关系实体 list
     * @return 影响条数
     */
    Integer insertBatch(@Param("userRoleDos") List<UserRoleDo> userRoleDos);
}
