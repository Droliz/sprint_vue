package com.qtw.common;

import lombok.Data;

/**
 * <p>
 *  返回数据格式
 * </p>
 *
 * @author qtw
 * @since 2022-12-23
 */
@Data
public class Result {

    private int code;  // 状态码
    private String msg;  // 成功/失败
    private Long total;  // 总记录
    private Object data;  // 数据

    // 失败
    public static Result fail(String msg) {
        return result(400, msg, 0L, null);
    }
    public static Result fail() {
        String msg = "失败";
        return result(400, msg, 0L, null);
    }

    // 成功  带数据
    public static Result success(Object data, Long total, String msg) {
        return result(200, msg, total, data);
    }
    public static Result success(Object data, Long total) {
        String msg = "成功";
        return result(200, msg, total, data);
    }

    // 成功  带数据
    public static Result success(Object data, String msg) {
        return result(200, msg, 0L, data);
    }
    public static Result success(Object data) {
        String msg = "成功";
        return result(200, "成功", 0L, data);
    }

    // 成功
    public static Result success(String msg) {
        return result(200, msg, 0L, null);
    }
    public static Result success() {
        String msg = "成功";
        return result(200, msg, 0L, null);
    }

    private static Result result(int code, String msg, Long total, Object data) {
        Result res = new Result();
        res.setData(data);
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        return res;
    }
}
