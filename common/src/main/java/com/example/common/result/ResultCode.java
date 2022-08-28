package com.example.common.result;

/**
 * author ye
 * createDate 2022/8/11  17:15
 */
public enum ResultCode implements StatusCode{
    SUCCESS(200, "请求成功"),
    FAILED(500, "请求失败");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
