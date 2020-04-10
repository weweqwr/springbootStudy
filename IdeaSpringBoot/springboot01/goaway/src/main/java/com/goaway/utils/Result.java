package com.goaway.utils;


public class Result {

    private boolean success;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result(ResultCode code) {
        this.setSuccess(code.success);
        this.setCode(code.code);
        this.setMessage(code.message);
    }

    public Result(ResultCode code,Object data) {
        this.setSuccess(code.success);
        this.setCode(code.code);
        this.setMessage(code.message);
        this.setData(data);
    }

    public Result(Integer code,String message,boolean success) {
        this.setCode(code);
        this.setMessage(message);
        this.setSuccess(success);
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
