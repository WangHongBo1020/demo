package com.example.whb_demo.enumo;

/**
 * @Author cjwl
 */
public enum CodeEnum {
    SUCCESS(0),
    ERROR(1),
    /**
     * token失效
     */
    TOKEN_FAILURE(10010),
    /**
     * 非法登录
     */
    ILLEGAL_LOGIN(10111),
    /**
     * 需重置密码 （目前只有dro有）
     */
    CHANGE_PASSWORD(10110),

    /**
     * openid 失效
     */
    OPENID_ERRPR(20010),

    /**
     * 登录用户信息错误
     */
    USER_INFO(10111);
    private Integer code;

    CodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
