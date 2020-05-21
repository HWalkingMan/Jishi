package com.jishi.jishi.entity.response;

public interface CommonError {
    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}
