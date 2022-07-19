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

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： 库位信息
 *
 * @author LiPengFei
 * @date 2021-11-01
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WMS—库位信息—实体", description = "仓库分区下位置分配业务字段信息")
@TableName("wms_stockroom_position")
public class WmsStockroomPosition implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 仓库-库位主键ID
     */
    @TableId(value = "position_id", type = IdType.INPUT)
    @ApiModelProperty("仓库-库位主键ID")
    private String positionId;

    /**
     * 库位编号
     */
    @TableField("position_code")
    @ApiModelProperty("库位编号")
    private String positionCode;

    /**
     * 库位状态：0空闲(默认)，1占用
     */
    @TableField("position_status")
    @ApiModelProperty("库位状态：0空闲(默认)，1占用")
    private Integer positionStatus;


    /**
     * 库位可用数量（默认8个）
     */
    @TableField("position_num")
    @ApiModelProperty("库位可用数量（默认8个）")
    private Integer positionNum;
    /**
     * 库区ID
     */
    @TableField("partition_id")
    @ApiModelProperty("库区ID")
    private String partitionId;

    /**
     * 库区名称
     */
    @TableField("partition_name")
    @ApiModelProperty("库区名称")
    private String partitionName;

    /**
     * 库区编码
     */
    @TableField("partition_code")
    @ApiModelProperty("库区编码")
    private String partitionCode;

    /**
     * 仓库ID
     */
    @TableField("stockroom_id")
    @ApiModelProperty("仓库ID")
    private String stockroomId;

    /**
     * 仓库名称
     */
    @TableField("stockroom_name")
    @ApiModelProperty("仓库名称")
    private String stockroomName;

    /**
     * 仓库编码
     */
    @TableField("stockroom_code")
    @ApiModelProperty("仓库编码")
    private String stockroomCode;

    /**
     * 仓库业务方编码
     */
    @TableField("business_code")
    @ApiModelProperty("仓库业务方编码")
    private String businessCode;

    /**
     * 是否删除：0不删(默认)，1删除
     */
    @TableField("is_deleted")
    @ApiModelProperty("是否删除：0不删(默认)，1删除")
    private Integer deleted;

    /**
     * 排序顺序
     */
    @TableField("sort_order")
    @ApiModelProperty("排序顺序")
    private Integer sortOrder;

    /**
     * 创建人名称
     */
    @TableField("create_user")
    @ApiModelProperty("创建人名称")
    private String createUser;

    /**
     * 创建人用户名
     */
    @TableField("create_username")
    @ApiModelProperty("创建人用户名")
    private String createUsername;

    /**
     * 创建日期
     */
    @TableField("create_time")
    @ApiModelProperty("创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改人ID
     */
    @TableField("update_user")
    @ApiModelProperty("修改人名称")
    private String updateUser;

    /**
     * 修改人名称
     */
    @TableField("update_username")
    @ApiModelProperty("修改人用户名")
    private String updateUsername;

    /**
     * 修改日期
     */
    @TableField("update_time")
    @ApiModelProperty("修改日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
