package com.example.whb_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(value = "WMS—库存明细—实体", description = "描述")
@TableName("wms_stockroom_memory")
public class WmsStockroomMemory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 库存明细主键id
     */
    @TableId(value = "stockroom_memory_id", type = IdType.INPUT)
    @ApiModelProperty("库存明细主键id")
    private String stockroomMemoryId;

    /**
     * 大区id
     */
    @TableField("region_id")
    @ApiModelProperty("大区id")
    private String regionId;

    /**
     * 大区名称
     */
    @TableField("region_name")
    @ApiModelProperty("大区名称")
    private String regionName;

    /**
     * 省公司id
     */
    @TableField("company_id")
    @ApiModelProperty("省公司id")
    private String companyId;

    /**
     * 省公司名称
     */
    @TableField("company_name")
    @ApiModelProperty("省公司名称")
    private String companyName;

    /**
     * 仓库id
     */
    @TableField("stockroom_id")
    @ApiModelProperty("仓库id")
    private String stockroomId;

    /**
     * 仓库编码
     */
    @TableField("stockroom_code")
    @ApiModelProperty("仓库编码")
    private String stockroomCode;

    /**
     * 仓库名称
     */
    @TableField("stockroom_name")
    @ApiModelProperty("仓库名称")
    private String stockroomName;

    /**
     * 库区id
     */
    @TableField("stockroom_partition_id")
    @ApiModelProperty("库区id")
    private String stockroomPartitionId;

    /**
     * 库区名称
     */
    @TableField("stockroom_partition_name")
    @ApiModelProperty("库区名称")
    private String stockroomPartitionName;

    /**
     * 库位id
     */
    @TableField("stockroom_position_id")
    @ApiModelProperty("库位id")
    private String stockroomPositionId;

    /**
     * 库位名称
     */
    @TableField("stockroom_position_code")
    @ApiModelProperty("库位号")
    private String stockroomPositionCode;

    /**
     * vin码
     */
    @TableField("vin")
    @ApiModelProperty("vin码")
    private String vin;

    /**
     * 品牌id
     */
    @TableField("brand_id")
    @ApiModelProperty("品牌id")
    private String brandId;

    /**
     * 品牌编号
     */
    @TableField("brand_code")
    @ApiModelProperty("品牌编号")
    private String brandCode;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    @ApiModelProperty("品牌名称")
    private String brandName;

    /**
     * 车型
     */
    @TableField("vehicle_model")
    @ApiModelProperty("车型")
    private String vehicleModel;

    /**
     * 车系
     */
    @TableField("vehicle_series")
    @ApiModelProperty("车系")
    private String vehicleSeries;

    /**
     * 车辆颜色
     */
    @TableField("vehicle_color")
    @ApiModelProperty("车辆颜色")
    private String vehicleColor;

    /**
     * 客户id
     */
    @TableField("clientele_id")
    @ApiModelProperty("客户id")
    private String clienteleId;

    /**
     * 客户名称
     */
    @TableField("clientele_name")
    @ApiModelProperty("客户名称")
    private String clienteleName;

    /**
     * 客户类型
     */
    @TableField("clientele_type")
    @ApiModelProperty("客户类型")
    private Integer clienteleType;

    /**
     * 入库单id
     */
    @TableField("stockroom_in_id")
    @ApiModelProperty("入库单id")
    private String stockroomInId;

    /**
     * 入库单号
     */
    @TableField("stockroom_in_code")
    @ApiModelProperty("入库单号")
    private String stockroomInCode;

    /**
     * 实际入库日期
     */
    @TableField("stockroom_in_date")
    @ApiModelProperty("实际入库日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomInDate;

    /**
     * 出库单id
     */
    @TableField("stockroom_out_id")
    @ApiModelProperty("出库单id")
    private String stockroomOutId;

    /**
     * 出库单号
     */
    @TableField("stockroom_out_code")
    @ApiModelProperty("出库单号")
    private String stockroomOutCode;

    /**
     * 实际出库日期
     */
    @TableField("stockroom_out_date")
    @ApiModelProperty("实际出库日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stockroomOutDate;

    /**
     * 库存状态：1：在库(入库完成后)，2：锁定(加入出库单后)，3：出库(出库完成后)
     */
    @TableField("stockroom_memory_status")
    @ApiModelProperty("库存状态：1：在库(入库完成后)，2：锁定(加入出库单后)，3：出库(出库完成后)")
    private Integer stockroomMemoryStatus;

    /**
     * 质量状态：0：无质损，1：有质损
     */
    @TableField("quality_status")
    @ApiModelProperty("质量状态：0：无质损，1：有质损")
    private Integer qualityStatus;

    /**
     * 在库天数
     */
    @TableField("stockroom_memory_days")
    @ApiModelProperty("在库天数")
    private String stockroomMemoryDays;

    /**
     * 是否删除：0不删(默认)，1删除
     */
    @TableField("is_deleted")
    @ApiModelProperty("是否删除：0不删(默认)，1删除")
    private Integer deleted;

    /**
     * 创建人名称
     */
    @TableField("create_user")
    @ApiModelProperty("创建人名称")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改人名称
     */
    @TableField("update_user")
    @ApiModelProperty("修改人名称")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField("update_time")
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    @ApiModelProperty("租户id")
    private String tenantId;

    /**
     * 错误信息
     */
    @TableField(exist = false)
    private List<String> error;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}