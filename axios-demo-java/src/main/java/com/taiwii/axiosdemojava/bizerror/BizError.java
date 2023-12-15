package com.taiwii.axiosdemojava.bizerror;

public class BizError implements IBizError {

    public static final IBizError SUCCESS = new BizError(200, "成功");
    public static final IBizError SYSTEM__UNCATCH_ERROR = new BizError(999999, "系统内部错误，请重试");
    public static final IBizError INVALID_PARAM = new BizError(30000, "参数错误");
    public static final IBizError HTTP_201 = new BizError(201, "Accepted");

    protected int code;
    protected String message;

    protected BizError(int code, String message) {
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
