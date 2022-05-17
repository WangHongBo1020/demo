package com.example.whb_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @author MaQun
 * @date 2021-11-01
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "客户品牌库区——实体", description = "描述")
@TableName("wms_brand_stockroom")
public class WmsBrandStockroom {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌库区ID
     */
    @TableId(value = "brand_reservoir_id", type = IdType.INPUT)
    @ApiModelProperty("品牌库区ID")
    private String brandReservoirId;

    /**
     * 租户ID
     */
    @TableField("tenant_id")
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 关联客户ID
     */
    @TableField("clientele_id")
    @ApiModelProperty("关联客户ID")
    private String clienteleId;

    /**
     * 客户名称
     */
    @TableField("clientele_name")
    @ApiModelProperty("客户名称")
    private String clienteleName;
    /**
     * 客户编码
     */
    @TableField("clientele_code")
    @ApiModelProperty("客户编码")
    private String clienteleCode;
    /**
     * 客户分类
     */
    @TableField("clientele_category")
    @ApiModelProperty("客户分类")
    private Integer clienteleCategory;

    /**
     * 品牌ID
     */
    @TableField("brand_id")
    @ApiModelProperty("品牌ID")
    private String brandId;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    @ApiModelProperty("品牌名称")
    private String brandName;

    /**
     * 品牌编码
     */
    @TableField("brand_code")
    @ApiModelProperty("品牌编码")
    private String brandCode;

    /**
     * 关联仓库ID
     */
    @TableField("stockroom_id")
    @ApiModelProperty("关联仓库ID")
    private String stockroomId;

    /**
     * 仓库名称
     */
    @TableField("stockroom_name")
    @ApiModelProperty("仓库名称")
    private String stockroomName;

    /**
     * 关联仓库库区ID
     */
    @TableField("stockroom_partition_id")
    @ApiModelProperty("关联仓库库区ID")
    private String stockroomPartitionId;

    /**
     * 仓库编码
     */
    @TableField("stockroom_code")
    @ApiModelProperty("仓库编码")
    private String stockroomCode;

    /**
     * 库区名称
     */
    @TableField("stockroom_partition_name")
    @ApiModelProperty("库区名称")
    private String stockroomPartitionName;

    /**
     * 库区编码
     */
    @TableField("stockroom_partition_code")
    @ApiModelProperty("库区编码")
    private String stockroomPartitionCode;

    /**
     * 删除：0 未删除；1 已删除
     */
    @TableField("is_deleted")
    @ApiModelProperty("删除：0 未删除；1 已删除")
    private Integer isDeleted;

    /**
     * 创建人
     */
    @TableField("create_user")
    @ApiModelProperty("创建人")
    private String createUser;

    /**
     * 创建人用户名
     */
    @TableField("create_username")
    @ApiModelProperty("创建人用户名")
    private String createUsername;

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
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
