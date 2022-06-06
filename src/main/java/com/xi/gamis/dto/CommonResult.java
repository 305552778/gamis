package com.xi.gamis.dto;

public class CommonResult<T> {
    private int Code;
    private T Data;
    private String Msg;
    private Object OptionalData;

    public Object getOptionalData() {
        return OptionalData;
    }

    public void setOptionalData(Object optionalData) {
        OptionalData = optionalData;
    }

    public CommonResult(int code, T data, String msg) {
        Code = code;
        Data = data;
        Msg = msg;
    }
    public CommonResult() {
    }

   public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
