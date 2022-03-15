package com.example.whb_demo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/22
 * \* Time: 13:36
 * \* Description:
 * \
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class WmsMemoryExcelVo {

    /**
     * 省公司名称
     */
    @Excel(name = "省公司名称",orderNum = "1", width = 20)
    private String companyName;

    /**
     * 仓库名称
     */
    @Excel(name = "仓库名称",orderNum = "2", width = 20)
    private String stockroomName;

    /**
     * 库区名称
     */
    @Excel(name = "库区名称",orderNum = "3", width = 20)
    private String stockroomPartitionName;

    /**
     * 库位号
     */
    @Excel(name = "库位号",orderNum = "4", width = 20)
    private String stockroomPositionCode;

    /**
     * vin码
     */
    @Excel(name = "vin码",orderNum = "5", width = 20)
    private String vin;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称",orderNum = "6", width = 20)
    private String brandName;

    /**
     * 车型
     */
    @Excel(name = "车型",orderNum = "7", width = 20)
    private String vehicleModel;

    /**
     * 车系
     */
    @Excel(name = "车系",orderNum = "8", width = 20)
    private String vehicleSeries;

    /**
     * 车辆颜色
     */
    @Excel(name = "车辆颜色",orderNum = "9", width = 20)
    private String vehicleColor;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称",orderNum = "10", width = 20)
    private String clienteleName;

    /**
     * 客户类型
     */
    @Excel(name = "客户类型",orderNum = "11", width = 20)
    private String clienteleType;

    /**
     * 实际入库日期
     */
    @Excel(name = "实际入库日期",orderNum = "12", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date stockroomInDate;

    /**
     * 在库天数
     */
    @Excel(name = "在库天数",orderNum = "13", width = 20)
    private String stockroomMemoryDays;

    /**
     * 质量状态：0：无质损，1：有质损
     */
    @Excel(name = "入库质量状态",orderNum = "14", width = 20)
    private String qualityStatus;

    /**
     * 钥匙数量
     */
    @Excel(name = "钥匙数量",orderNum = "15", width = 20)
    private Integer keyAmount;

    /**
     * 钥匙是否随车 0：否，1：是
     */
    @Excel(name = "钥匙是否随车",orderNum = "16", width = 20)
    private String keyFollow;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号",orderNum = "17", width = 20)
    private String vehicleNumber;
    /**
     * 随车附件
     */
    @Excel(name = "随车附件",orderNum = "18", width = 20)
    private String followBackups;

    /**
     * 附件说明
     */
    @Excel(name = "附件说明",orderNum = "19", width = 20)
    private String followDescribe;

}
