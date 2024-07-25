package com.example.whb_demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: Liuk
 * @Date: 2023-05-29
 * @Description: 费率明细
 */
@Data
@TableName("rate_detail_oa_staging")
public class RateDetailOaStaging implements Serializable {


    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 企业编码
     */
    private String enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 子公司编号
     */
    private String companyCode;

    /**
     * 子公司名称
     */
    private String companyName;

    /**
     * 一级经营组织编号
     */
    private String firstOrgCode;

    /**
     * 一级经营组织名称
     */
    private String firstOrgName;

    /**
     * 二级经营组织编号
     */
    private String secondOrgCode;

    /**
     * 二级经营组织名称
     */
    private String secondOrgName;

    /**
     * 三级经营组织编号
     */
    private String thridOrgCode;

    /**
     * 三级经营组织名称
     */
    private String thridOrgName;

    /**
     * 四级经营组织编号
     */
    private String fourOrgCode;

    /**
     * 四级经营组织名称
     */
    private String fourOrgName;

    /**
     * 费率类型编号
     */
    private String rateTypeCode;

    /**
     * 费率类型名称
     */
    private String rateTypeName;

    /**
     * 费率头ID
     */
    private String rateHeaderId;

    /**
     * 费率头代码
     */
    private String rateHeaderCode;

    /**
     * 费率头名称
     */
    private String rateHeaderName;

    /**
     * 业务类型编号
     */
    private String businessTypeCode;

    /**
     * 业务类型名称
     */
    private String businessTypeName;

    /**
     * 单据类型编码
     */
    private String documentTypeCode;

    /**
     * 单据类型名称
     */
    private String documentTypeName;

    /**
     * 规则编号
     */
    private String ruleCode;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 应收应付状态编号
     */
    private String paymentStatusCode;

    /**
     * 应收应付状态名称
     */
    private String paymentStatusName;

    /**
     * 是否更正：0:否 ，1:是
     */
    private String whetherAlteration;

    /**
     * 费率明细编码
     */
    private String rateDetailCode;

    /**
     * 客户编码
     */
    private String customerCode;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

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
     * 费率生效时间
     */
    private Date rateEffectiveTime;

    /**
     * 费率失效时间
     */
    private Date rateFailureTime;

    /**
     * 是否导入成功：0：暂存，1：失败，2：成功
     */
    private String importState;

    /**
     * 备用字段1
     */
    private String attribute001;

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
     * 备用字段8
     */
    private String attribute008;

    /**
     * 备用字段9
     */
    private String attribute009;

    /**
     * 备用字段10
     */
    private String attribute010;

    /**
     * 备用字段11
     */
    private String attribute011;

    /**
     * 备用字段12
     */
    private String attribute012;

    /**
     * 备用字段13
     */
    private String attribute013;

    /**
     * 备用字段14
     */
    private String attribute014;

    /**
     * 备用字段15
     */
    private String attribute015;

    /**
     * 备用字段16
     */
    private String attribute016;

    /**
     * 备用字段17
     */
    private String attribute017;

    /**
     * 备用字段18
     */
    private String attribute018;

    /**
     * 备用字段19
     */
    private String attribute019;

    /**
     * 备用字段20
     */
    private String attribute020;

    /**
     * 时间备用字段1
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "发车时间从",orderNum = "1", width = 20)
    private Date attributeDate1;

    /**
     * 时间备用字段2
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "发车时间到",orderNum = "1", width = 20)
    private Date attributeDate2;

    /**
     * 时间备用字段3
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate3;

    /**
     * 时间备用字段4
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate4;

    /**
     * 时间备用字段5
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate5;

    /**
     * 时间备用字段6
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate6;

    /**
     * 时间备用字段7
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate7;

    /**
     * 时间备用字段8
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate8;

    /**
     * 时间备用字段9
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate9;

    /**
     * 时间备用字段10
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attributeDate10;

    /**
     * 数值备用字段1
     */
    @Excel(name = "单价",orderNum = "1", width = 20)
    private BigDecimal attributeNumber1;

    /**
     * 数值备用字段2
     */
    @Excel(name = "结算里程",orderNum = "1", width = 20)
    private BigDecimal attributeNumber2;

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
     * 数值备用字段5
     */
    private BigDecimal attributeNumber5;

    /**
     * 数值备用字段6
     */
    private BigDecimal attributeNumber6;

    /**
     * 数值备用字段7
     */
    private BigDecimal attributeNumber7;

    /**
     * 数值备用字段8
     */
    private BigDecimal attributeNumber8;

    /**
     * 数值备用字段9
     */
    private BigDecimal attributeNumber9;

    /**
     * 数值备用字段10
     */
    private BigDecimal attributeNumber10;

    /**
     * 错误原因，导入失败时返回
     */
    @ApiModelProperty(value = "错误原因，导入失败时返回")
    private String importFailRemark;

    /**
     * 流程id
     */
    @ApiModelProperty(value = "流程id")
    private String requestId;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private String dataSource;

    private String statusCode;

    private String statusName;


}
