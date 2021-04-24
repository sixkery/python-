package com.sixkery.kike.admin.constant;

/**
 * @author: sixkery
 * @date:2021/4/3
 */
public class SecurityConstant {
    private SecurityConstant() {
    }

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_HEAD = "Bearer ";
    public static final String SECRET = "eyJleHAiOjE1NDMyMDUyODUsInN1YiI6ImFkbWluIiwiY3JlYXRlZCI6MTU0MDYxMzI4N";
    public static final Long EXPIRATION = 1000 * 60 * 60 * 12L;
    /**
     * 用户禁用状态
     */
    public static final Integer USER_STATUS_LOCK = -1;
    public static final String POST = "POST";


}
