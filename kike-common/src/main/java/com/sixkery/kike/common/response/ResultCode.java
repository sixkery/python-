package com.sixkery.kike.common.response;


/**
 * 枚举了一些常用 API 操作码
 *
 * @author sixkery
 * @date 2019/4/19
 */
public enum ResultCode implements ErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功！"),
    /**
     * 操作失败
     */
    FAILED(20001, "操作失败"),

    PASSWORD_ERROR(20002, "密码不正确"),

    USER_NOT_EXIST_EXCEPTION(20003, "用户不存在"),

    VALIDATE_FAILED(20004, "该角色已被使用，无法删除"),
    /**
     * 暂未登录或 token 已经过期
     */
    UNAUTHORIZED(20005, "暂未登录或token已经过期"),
    FORBIDDEN(20006, "没有相关权限"),
    TOKEN_NOTFOUND(20006, "没有相关权限"),
    API_PARAMS_ERROR(20007, "参数校验异常"),
    NULL_POINT_ERROR(20008, "空指针异常！");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 返回信息
     */
    private final String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
