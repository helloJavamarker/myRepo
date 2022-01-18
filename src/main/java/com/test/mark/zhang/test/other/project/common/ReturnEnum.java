package com.test.mark.zhang.test.other.project.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by mark
 * @Classname ReturnEnum
 * @Description TODO
 * @Date 2021/12/13 5:05 下午
 */

@AllArgsConstructor
@Getter
public enum ReturnEnum {
    /**
     *
     */
    OK(0, "成功","success"),
    SUCCESS(200, "成功","success"),
    UNAUTHORIZED(100, "需要认证","unauthorized"),
    FORBIDDEN(99, "没有接口权限","forbidden"),
    TOO_MANY_REQUESTS(98, "调用超过最大次数","too_many_requests"),
    BAD_REQUEST(97, "参数不正确","bad_request"),
    BAD_CREDENTIAL(96, "用户名或密码不正确","bad_credential"),
    TOKEN_EXPIRED(95, "token过期","token_expired"),
    TOKEN_INVALID(94, "token解析失败或无效","token_invalid"),
    NOT_FOUND(93, "资源不存在","not_found"),
    REGISTER_ERROR(92, "无上级节点或上级节点未认证","register_error"),
    DECRYPT_ERROR(91, "解密失败","decrypt_error"),
    INTERNAL_SERVER_ERROR(90, "服务器内部错误","internal_server_error"),
    CONNECTION_ERROR(89, "接口调用异常","connection_error");

    private int code;
    private String msg;
    private String errorCode;

}
