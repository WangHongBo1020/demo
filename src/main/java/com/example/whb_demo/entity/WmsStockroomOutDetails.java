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

import java.util.Date;

/**
 * 描述： TODO
 *
 * @author xxx
 * @date 2021-11-11
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "出库单明细——实体", description = "描述")
@TableName("wms_stockroom_out_details")
public class WmsStockroomOutDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    //@TableField("car_details_id")
    @TableId(value = "car_details_id", type = IdType.INPUT)
    @ApiModelProperty("主键ID")
    private String carDetailsId;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @TableField(value = "user_id")
    private String userId;

    /**
     * 出库单主键ID
     */
    @TableField("stockroom_out_id")
    @ApiModelProperty("出库单主键ID")
    private String stockroomOutId;

    /**
     * 出库单号
     */
    @TableField("stockroom_out_no")
    @ApiModelProperty("出库单号")
    private String stockroomOutNo;

    /**
     * 库存明细ID
     */
    @TableField("stockroom_memory_id")
    @ApiModelProperty("库存明细ID")
    private String stockroomMemoryId;

    /**
     * vin码
     */
    @TableField("vin")
    @ApiModelProperty("vin码")
    private String vin;

    /**
     * 车辆品牌ID
     */
    @TableField("vehicle_brand_id")
    @ApiModelProperty("车辆品牌ID")
    private String vehicleBrandId;

    /**
     * 车系
     */
    @TableField("vehicle_series")
    @ApiModelProperty("车系")
    private String vehicleSeries;

    /**
     * 车型
     */
    @TableField("vehicle_model")
    @ApiModelProperty("车型")
    private String vehicleModel;

    /**
     * 车牌号
     */
    @TableField("vehicle_number")
    @ApiModelProperty("车牌号")
    private String vehicleNumber;

    /**
     * 车辆颜色
     */
    @TableField("vehicle_color")
    @ApiModelProperty("车辆颜色")
    private String vehicleColor;

    /**
     * 车辆状态
     */
    @TableField("vehicle_state")
    @ApiModelProperty("车辆状态")
    private Integer vehicleState;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    @ApiModelProperty("品牌名称")
    private String brandName;

    /**
     * 钥匙数量
     */
    @TableField("key_number")
    @ApiModelProperty("钥匙数量")
    private String keyNumber;

    /**
     * 钥匙是否随车
     */
    @TableField("key_follow")
    @ApiModelProperty(value = "钥匙是否随车 0： 不随车，1：随车", required = true)
    private Integer keyFollow;

    /**
     * 随车附件
     */
    @TableField("accessory")
    @ApiModelProperty("随车附件")
    private String accessory;

    /**
     * 附件说明
     */
    @TableField("accessory_explain")
    @ApiModelProperty("附件说明")
    private String accessoryExplain;

    /**
     * 附件照片
     */
    @TableField("accessory_imager")
    @ApiModelProperty("附件照片")
    private String accessoryImager;

    /**
     * 库位号
     */
    @TableField("storage_location_no")
    @ApiModelProperty("库位号")
    private String storageLocationNo;

    /**
     * 库位号解除时间
     */
    @TableField("storage_location_out_time")
    @ApiModelProperty("库位号解除时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date storageLocationOutTime;

    /**
     * 道路编号
     */
    @TableField("road_no")
    @ApiModelProperty("道路编号")
    private String roadNo;

    /**
     * 分配道路编号时间
     */
    @TableField("road_time")
    @ApiModelProperty("分配道路编号时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date roadTime;

    /**
     * 实际出库时间
     */
    @TableField("eality_tiime")
    @ApiModelProperty("实际出库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ealityTiime;

    /**
     * 备注
     */
    @TableField("remark")
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 质损部位
     */
    @TableField("quality_part")
    @ApiModelProperty("质损部位数量")
    private Integer qualityPart;

    /**
     * 质损状态
     */
    @TableField("quality_state")
    @ApiModelProperty(value = "质损状态 0： 无，1：有", required = true)
    private Integer qualityState;

    /**
     * 质损信息
     */
    @TableField("quality_information")
    @ApiModelProperty("质损信息")
    private String qualityInformation;

    /**
     * 质损说明
     */
    @TableField("quality_explain")
    @ApiModelProperty("质损部位")
    private String qualityExplain;

    /**
     * 质损图片
     */
    @TableField("quality_image")
    @ApiModelProperty("质损图片")
    private String qualityImage;

    /**
     * 删除：0 未删除；1 已删除
     */
    @TableField("is_deleted")
    @ApiModelProperty("删除：0 未删除；1 已删除")
    private Integer deleted;

    /**
     * 创建人
     */
    @TableField("create_user")
    @ApiModelProperty("创建人")
    private String createUser;

    /**
     * 更新人
     */
    @TableField("update_user")
    @ApiModelProperty("更新人")
    private String updateUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 打印备车单时间
     */
    @TableField("prepare_time")
    @ApiModelProperty("打印备车单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date prepareTime;

    /**
     * 备车单打印次数
     */
    @TableField("prepare_num")
    @ApiModelProperty("备车单打印次数")
    private Integer prepareNum;

    /**
     * 移动端扫码/查询时间
     */
    @TableField("vin_time")
    @ApiModelProperty("移动端扫码/查询时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date vinTime;

    /**
     * 验车时间
     */
    @TableField("quality_time")
    @ApiModelProperty("验车时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qualityTime;

    /**
     * 打印出库单时间
     */
    @TableField("print_out_time")
    @ApiModelProperty("打印出库单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date printOutTime;
    /**
     * 出库单打印次数
     */
    @TableField("print_out_num")
    @ApiModelProperty("出库单打印次数")
    private Integer printOutNum;

    /**
     * 出库方式
     */
    @TableField("stockroom_out_mode")
    @ApiModelProperty("出库方式")
    private Integer stockroomOutMode;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}