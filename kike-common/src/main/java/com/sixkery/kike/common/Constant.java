package com.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 常量
 *
 * @author sixkery
 * @date 2020/7/31
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final String DELIMITER_TO = "@";
    public static final String DELIMITER_COLON = ":";

    /**
     * 用户默认头像
     */
    public static final String USER_DEFAULT_AVATAR = "https://ooo.0o0.ooo/2019/04/28/5cc5a71a6e3b6.png";

    /**
     * 用户正常状态
     */
    public static final Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    public static final Integer USER_STATUS_LOCK = -1;

    /**
     * 普通用户
     */
    public static final Integer USER_TYPE_NORMAL = 0;

    /**
     * 管理员
     */
    public static final Integer USER_TYPE_ADMIN = 1;

    /**
     * 全部数据权限
     */
    public static final Integer DATA_TYPE_ALL = 0;

    /**
     * 自定义数据权限
     */
    public static final Integer DATA_TYPE_CUSTOM = 1;

    /**
     * 本部门及以下
     */
    public static final Integer DATA_TYPE_UNDER = 2;

    /**
     * 本部门
     */
    public static final Integer DATA_TYPE_SAME = 3;

    /**
     * 正常状态
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    public static final Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    public static final Integer DEL_FLAG = 1;

    /**
     * 限流前缀
     */
    public static final String LIMIT_PRE = "XBOOT_LIMIT:";

    /**
     * 限流标识
     */
    public static final String LIMIT_ALL = "XBOOT_LIMIT_ALL";

    /**
     * 顶部菜单类型权限
     */
    public static final Integer PERMISSION_NAV = -1;

    /**
     * 页面类型权限
     */
    public static final Integer PERMISSION_PAGE = 0;

    /**
     * 操作类型权限
     */
    public static final Integer PERMISSION_OPERATION = 1;

    /**
     * 1级菜单父id
     */
    public static final String PARENT_ID = "0";

    /**
     * 0级菜单
     */
    public static final Integer LEVEL_ZERO = 0;

    /**
     * 1级菜单
     */
    public static final Integer LEVEL_ONE = 1;

    /**
     * 2级菜单
     */
    public static final Integer LEVEL_TWO = 2;

    /**
     * 3级菜单
     */
    public static final Integer LEVEL_THREE = 3;

    /**
     * 部门负责人类型 主负责人
     */
    public static final Integer HEADER_TYPE_MAIN = 0;

    /**
     * 部门负责人类型 副负责人
     */
    public static final Integer HEADER_TYPE_VICE = 1;
}
