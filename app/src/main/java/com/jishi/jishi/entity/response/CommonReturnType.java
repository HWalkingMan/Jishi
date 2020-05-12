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

    @Override
    public String toString() {
        return "CommonReturnType{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
