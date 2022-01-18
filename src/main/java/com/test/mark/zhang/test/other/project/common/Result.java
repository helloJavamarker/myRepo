package com.test.mark.zhang.test.other.project.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * @author by mark
 * @Classname Result
 * @Description TODO
 * @Date 2021/12/13 4:59 下午
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private Boolean success;
    private String errorCode;
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg, T data, Boolean success) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Boolean success, String errorCode, Integer code, String msg, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Boolean success, String errorCode, Integer code, String msg) {
        this.success = success;
        this.errorCode = errorCode;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ReturnEnum.SUCCESS.getCode(), ReturnEnum.SUCCESS.getMsg(), data, true);
    }

    public static <T> Result<T> success() {
        return new Result<>(ReturnEnum.SUCCESS.getCode(), ReturnEnum.SUCCESS.getMsg(), null, true);
    }

    public static <T> Result<T> ok() {
        return new Result<>(ReturnEnum.SUCCESS.getCode(), ReturnEnum.SUCCESS.getMsg(), null, true);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ReturnEnum.SUCCESS.getCode(), ReturnEnum.SUCCESS.getMsg(), data, true);
    }


    public static <T> Result<T> response(ReturnEnum returnEnum, T data, Boolean success) {
        return new Result<>(returnEnum.getCode(), returnEnum.getMsg(), data, success);
    }

    public static <T> Result<T> fail(ReturnEnum returnEnum) {
        return new Result<T>(false, returnEnum.getErrorCode(), returnEnum.getCode(), returnEnum.getMsg());
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(false, ReturnEnum.INTERNAL_SERVER_ERROR.getErrorCode(), ReturnEnum.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

}
