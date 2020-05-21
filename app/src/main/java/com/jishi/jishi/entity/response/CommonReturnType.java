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
        CommonReturnType result = setEmBusinessErr(EmBusinessErr.SUCCESS,
                EmBusinessErr.SUCCESS.getErrMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static CommonReturnType errorResult(EmBusinessErr enums) {
        return setEmBusinessErr(enums, enums.getErrMsg());
    }

    public static CommonReturnType errorResult(EmBusinessErr enums, String errorMessage) {
        return setEmBusinessErr(enums, errorMessage);
    }

    public static CommonReturnType setEmBusinessErr(EmBusinessErr enums) {
        return okResult(enums.getErrCode(), enums.getErrMsg());
    }

    private static CommonReturnType setEmBusinessErr(EmBusinessErr enums, String errorMessage) {
        return okResult(enums.getErrCode(), errorMessage);
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

    @Override
    public String toString() {
        return "CommonReturnType{" + "data=" + data + ", errorCode=" + errorCode + ", errorMsg='"
                + errorMsg + '\'' + '}';
    }
}
