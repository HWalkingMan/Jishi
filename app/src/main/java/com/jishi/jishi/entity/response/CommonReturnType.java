package com.jishi.jishi.entity.response;

/**
 * @Description
 * @Author WM
 * @Date 2020/2/29 12:01
 */
public class CommonReturnType<T> {

    private T data;
    private int errorCode;
    private String errorMsg;

    public CommonReturnType() {
        errorCode = 0;
    }

    public static CommonReturnType build(Object data) {
        CommonReturnType returnType = new CommonReturnType();
        returnType.data = data;
        returnType.errorCode = 0;
        returnType.errorMsg = "success";
        return returnType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static CommonReturnType errorResult(int code, String msg) {
        CommonReturnType result = new CommonReturnType();
        return result.error(code, msg);
    }

    public static CommonReturnType okResult(int code, String msg) {
        CommonReturnType result = new CommonReturnType();
        return result.ok(code, null, msg);
    }

    public static CommonReturnType okResult(Object data) {
        CommonReturnType result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS,
                AppHttpCodeEnum.SUCCESS.getErrorMessage());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static CommonReturnType errorResult(AppHttpCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getErrorMessage());
    }

    public static CommonReturnType errorResult(AppHttpCodeEnum enums, String errorMessage) {
        return setAppHttpCodeEnum(enums, errorMessage);
    }

    public static CommonReturnType setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getErrorMessage());
    }

    private static CommonReturnType setAppHttpCodeEnum(AppHttpCodeEnum enums, String errorMessage) {
        return okResult(enums.getCode(), errorMessage);
    }

    public CommonReturnType<?> error(Integer code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
        return this;
    }

    public CommonReturnType<?> ok(Integer code, T data) {
        this.errorCode = code;
        this.data = data;
        return this;
    }

    public CommonReturnType<?> ok(Integer code, T data, String msg) {
        this.errorCode = code;
        this.data = data;
        this.errorMsg = msg;
        return this;
    }

    public CommonReturnType<?> ok(T data) {
        this.data = data;
        return this;
    }
}
