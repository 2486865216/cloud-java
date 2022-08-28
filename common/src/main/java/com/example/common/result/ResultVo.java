package com.example.common.result;

/**
 * author ye
 * createDate 2022/8/11  17:19
 */
public class ResultVo {
    private int code;
    private String message;
    private Object data;

    public ResultVo() {
    }

    public ResultVo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //默认成功
    public ResultVo(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }

    //返回指定状态码
    public ResultVo(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    //只返回状态码
    public ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
