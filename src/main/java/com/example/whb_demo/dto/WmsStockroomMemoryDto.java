package com.example.whb_demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述： TODO
 *
 * @author LiPengFei
 * @date 2021-11-11
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WMS—库存明细—dto")
public class WmsStockroomMemoryDto implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    @ApiModelProperty("库存明细主键id")
    private String stockroomMemoryId;

    @ApiModelProperty("大区id")
    private String regionId;

    @ApiModelProperty("大区名称")
    private String regionName;

    @ApiModelProperty("省公司id")
    private String companyId;

    @ApiModelProperty("省公司名称")
    private String companyName;

    @ApiModelProperty("仓库id")
    private String stockroomId;

    @ApiModelProperty("仓库编码")
    private String stockroomCode;

    @ApiModelProperty("仓库名称")
    private String stockroomName;

    @ApiModelProperty("库区id")
    private String stockroomPartitionId;

    @ApiModelProperty("库区名称")
    private String stockroomPartitionName;

    @ApiModelProperty("库位id")
    private String stockroomPositionId;

    @ApiModelProperty("库位号")
    private String stockroomPositionCode;

    @ApiModelProperty("vin码")
    private String vin;

    @ApiModelProperty("品牌id")
    private String brandId;

    @ApiModelProperty("品牌编号")
    private String brandCode;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("车型")
    private String vehicleModel;

    @ApiModelProperty("车系")
    private String vehicleSeries;

    @ApiModelProperty("车辆颜色")
    private String vehicleColor;

    @ApiModelProperty("客户id")
    private String clienteleId;

    @ApiModelProperty("客户名称")
    private String clienteleName;

    @ApiModelProperty("客户类型")
    private Integer clienteleType;

    @ApiModelProperty("入库单id")
    private String stockroomInId;

    @ApiModelProperty("入库单号")
    private String stockroomInCode;

    @ApiModelProperty("实际入库日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomInDate;

    @ApiModelProperty("出库单id")
    private String stockroomOutId;

    @ApiModelProperty("出库单号")
    private String stockroomOutCode;

    @ApiModelProperty("实际出库日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomOutDate;

    @ApiModelProperty("库存状态：1：在库(入库完成后)，2：锁定(加入出库单后)，3：出库(出库完成后)")
    private Integer stockroomMemoryStatus;

    @ApiModelProperty("质量状态：0：无质损，1：有质损")
    private Integer qualityStatus;

    @ApiModelProperty("是否删除：0不删(默认)，1删除")
    private Integer deleted;

    @ApiModelProperty("创建人名称")
    private String createUser;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改人名称")
    private String updateUser;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("在库天数")
    private Integer stockroomMemoryDays;

    /**
     * 当前登录用户ID
     */
    @ApiModelProperty("当前登录用户ID")
    private String loginUserId;

    /**
     * 入库日期 -开始时间
     */
    @ApiModelProperty("入库日期 -开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomInDateStart;

    /**
     * 入库日期 -结束时间
     */
    @ApiModelProperty("入库日期 -结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomInDateEnd;

    /**
     * 出库日期 -开始时间
     */
    @ApiModelProperty("出库日期 -开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomOutDateStart;

    /**
     * 出库日期 -结束时间
     */
    @ApiModelProperty("出库日期 -结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomOutDateEnd;

    /**
     * 在库天数 - 起始
     */
    @ApiModelProperty("在库天数 - 起始")
    private Integer stockroomMemoryDaysBegin;

    /**
     * 在库天数 - 结束
     */
    @ApiModelProperty("在库天数 - 结束")
    private Integer stockroomMemoryDaysAfter;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}