package com.example.whb_demo.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>返回前端分页信息</p>
 * <p>
 *     不能单独使用，需set到ResultEntity
 * </p>
 *
 * @author cjwl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页信息")
public class Page implements Serializable {

    private static final long serialVersionUID = -275582248840137389L;

    /**
     * 当前页
     * 初始化值 1
     */
    @ApiModelProperty("分页： 当前页码")
    private int currentPage = 1;
    /**
     * 总页数
     */
    @ApiModelProperty("分页： 总页码")
    private int totalPage;
    /**
     * 每页数量大小
     * 初始化值为20
     */
    @ApiModelProperty("分页： 页面大小")
    private int pageSize = 20;
    /**
     * 总数
     */
    @ApiModelProperty("分页： 总记录数")
    private long total;


    /**
     * <p>构造分页结果对象</p>
     *
     * @param currentPage 当前页号
     * @param pageSize    每一页大小
     * @param total       数据总量
     */
    public Page(int currentPage, int pageSize, long total) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = getPages(pageSize, total);
    }

    /**
     * <p>创建page对象</p>
     *
     * @param currentPage 当前页号
     * @param pageSize    每一页大小
     * @param total       数据总量
     * @return
     */
    public static   Page of(int currentPage, int pageSize, long total) {
          Page page = new   Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(total);
        page.setTotalPage(getPages(pageSize, total));
        return page;
    }

    /**
     * <p>创建一个空分页对象</p>
     *
     * @param currentPage 当前页号
     * @param pageSize    每一页大小
     * @return
     */
    public static Page of(int currentPage, int pageSize) {
          Page page = new   Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(0);
        page.setTotalPage(0);
        return page;
    }

    static int getPages(int pageSize, long total) {
        if (pageSize == 0) {
            return 0;
        } else {
            long pages = total / pageSize;
            if (total % pageSize != 0) {
                ++pages;
            }

            return (int) pages;
        }
    }
}
