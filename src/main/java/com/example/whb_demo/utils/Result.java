package com.example.whb_demo.utils;

import com.example.whb_demo.constant.GlobalConstants;
import com.example.whb_demo.enumo.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>给前端数据封装类</p>
 * <p>泛型说明： </p>
 * <p> T 表示 Object   返回类型</p>
 * <p> E 表示 List<E>  返回类型</p>
 *
 * <p>
 * <p>
 * 数据结构
 * {
 * code:,
 * message:,
 * entiy:{
 * data:,
 * list:
 * page:{
 * total:
 * totalpage:
 * currentpage:
 * pagesize:
 * }}
 * }
 * </p>
 *
 * @Author cjwl
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("前端请求统一返回结果集")
public class Result<T, E> implements Serializable {

    /**
     * 编码
     */
    @ApiModelProperty("请求结果数值 0：成功  1：失败")
    private Integer code;
    /**
     * 消息
     */
    @ApiModelProperty("请求返回消息，如果失败可能回返回错误提示信息")
    private String message;
    /**
     * 数据结果
     */
    @ApiModelProperty("返回结果封装实体集，具体返回业务数据集合")
    private ResultEntity<T, E> entity;


    public static <T, E> Result<T, E> succeed(String message) {
        return of(CodeEnum.SUCCESS.getCode(), message, null);
    }

    public static <T, E> Result<T, E> succeed(ResultEntity<T, E> model, String message) {
        return of(CodeEnum.SUCCESS.getCode(), message, model);
    }

    public static <T, E> Result<T, E> of() {
        return new Result<>(CodeEnum.SUCCESS.getCode(), GlobalConstants.SUCCEED, null);
    }

    public static <T, E> Result<T, E> of(Integer code, String message, ResultEntity<T, E> model) {
        return new Result<>(code, message, (ResultEntity) model);
    }

    public static <T, E> Result<T, E> failed() {
        return of(CodeEnum.ERROR.getCode(), GlobalConstants.FAILED, null);
    }

    public static <T, E> Result<T, E> failed(String message) {
        return of(CodeEnum.ERROR.getCode(), message, null);
    }

    public static <T, E> Result<T, E> failed(String message, Integer code) {
        return of(code, message, null);
    }

    public static <T, E> Result<T, E> failed(ResultEntity<T, E> model, String message) {
        return of(CodeEnum.ERROR.getCode(), message, model);
    }


    /**
     * 只返回data
     *
     * @param data
     * @return
     */
    public static Result succeed(Object data) {
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(data, new ArrayList(), null));
        return result;
    }

    /**
     * 只返回data,message
     *
     * @param data
     * @return
     */
    public static Result succeed(Object data, String message) {
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(data, new ArrayList(), null));
        result.setMessage(message);
        return result;
    }

    public static Result succeed(Object data, List list) {
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(data, list, null));
        return result;
    }

    /**
     * 只返回list
     *
     * @param list
     * @return
     */
    public static Result succeed(List list) {
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(null, list, null));
        return result;
    }


    /**
     * 返回分页,成功数据
     *
     * @param list
     * @param currentPage
     * @param pageSize
     * @param total
     * @return
     */
    public static Result succeed(List list, long currentPage, long pageSize, long total) {
        Page page = Page.of((int) currentPage, (int) pageSize, total);
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(null, list, page));
        return result;
    }

    /**
     * 返回分页,成功数据
     *
     * @param data
     * @param list
     * @param currentPage
     * @param pageSize
     * @param total
     * @return
     */
    public static Result succeed(Object data, List list, long currentPage, long pageSize, long total) {
        Page page = Page.of((int) currentPage, (int) pageSize, total);
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(data, list, page));
        return result;
    }

    /**
     * 返回分页,成功数据
     *
     * @param list
     * @param page
     * @return
     */
    public static Result succeed(List list, Page page) {
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(list, page));
        return result;
    }

    /**
     * 返回分页,成功数据
     *
     * @param list
     * @param currentPage
     * @param pageSize
     * @param total
     * @param data
     * @return
     */
    public static Result<Object, Object> succeed(List list, long currentPage, long pageSize, long total, Object data) {
        Page page = Page.of((int) currentPage, (int) pageSize, total);
        Result<Object, Object> result = Result.of();
        result.setEntity(ResultEntity.of(data, list, page));
        return result;
    }


}
