package com.example.whb_demo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * \* User: wanghongbo
 * \* Date: 2024/1/23 14:42
 * \* Description:
 * \
 */
@Data
public class RateDetailOaStagingExcelVo implements Serializable {

    /**
     * 品牌编码
     */
    @Excel(name = "品牌代码",orderNum = "1", width = 20)
    private String brandCode;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称",orderNum = "1", width = 20)
    private String brandName;

    /**
     * 备用字段6
     */
    @Excel(name = "客户名称",orderNum = "1", width = 20)
    private String attribute006;

    /**
     * 备用字段7
     */
    @Excel(name = "TMS订单类型",orderNum = "1", width = 20)
    private String attribute007;

    /**
     * 备用字段2
     */
    @Excel(name = "起始地",orderNum = "1", width = 20)
    private String attribute002;

    /**
     * 备用字段3
     */
    @Excel(name = "目的地",orderNum = "1", width = 20)
    private String attribute003;

    /**
     * 备用字段4
     */
    @Excel(name = "计费车型",orderNum = "1", width = 20)
    private String attribute004;

    /**
     * 备用字段5
     */
    @Excel(name = "运输方式",orderNum = "1", width = 20)
    private String attribute005;

    /**
     * 数值备用字段2
     */
    @Excel(name = "结算里程",orderNum = "1", width = 20)
    private BigDecimal attributeNumber2;
    /**
     * 数值备用字段1
     */
    @Excel(name = "单价",orderNum = "1", width = 20)
    private BigDecimal attributeNumber1;

    /**
     * 数值备用字段3
     */
    @Excel(name = "税率",orderNum = "1", width = 20)
    private BigDecimal attributeNumber3;

    /**
     * 数值备用字段4
     */
    @Excel(name = "管理费率",orderNum = "1", width = 20)
    private BigDecimal attributeNumber4;

    /**
     * 时间备用字段1
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发车时间从",orderNum = "1", width = 20)
    //private Date attributeDate1;
    private String attributeDate1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发车时间到",orderNum = "1", width = 20)
    //private Date attributeDate2;
    private String attributeDate2;




}
