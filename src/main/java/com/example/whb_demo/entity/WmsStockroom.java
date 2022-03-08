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
 * 描述：wms仓库信息
 *
 * @author LiPengFei
 * @date 2021-11-01
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WMS—仓库信息—实体", description = "公司下仓库业务字段信息")
@TableName("wms_stockroom")
public class WmsStockroom implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "stockroom_id", type = IdType.INPUT)
    @ApiModelProperty("主键")
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
     * 仓库别名
     */
    @TableField("stockroom_alias")
    @ApiModelProperty("仓库别名")
    private String stockroomAlias;

    /**
     * 业务方编码
     */
    @TableField("business_code")
    @ApiModelProperty("业务方编码")
    private String businessCode;

    /**
     * 租户ID
     */
    @TableField("tenant_id")
    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 区域编号
     */
    @TableField("region_id")
    @ApiModelProperty("区域编号")
    private String regionId;

    /**
     * 区域名称
     */
    @TableField("region_name")
    @ApiModelProperty("区域名称")
    private String regionName;

    /**
     * 区域编码
     */
    @TableField("region_code")
    @ApiModelProperty("区域编码")
    private String regionCode;

    /**
     * 公司(仓库组织数)ID
     */
    @TableField("company_id")
    @ApiModelProperty("公司(仓库组织数)ID")
    private String companyId;

    /**
     * 仓库业务类型
     */
    @TableField("stockroom_type")
    @ApiModelProperty("仓库业务类型")
    private String stockroomType;

    /**
     * 仓库地区-省
     */
    @TableField("province_id")
    @ApiModelProperty("仓库地区-省")
    private String provinceId;

    /**
     * 仓库地区-市
     */
    @TableField("city_id")
    @ApiModelProperty("仓库地区-市")
    private String cityId;

    /**
     * 仓库地区-区
     */
    @TableField("area_id")
    @ApiModelProperty("仓库地区-区")
    private String areaId;

    /**
     * 仓库地区-具体地址
     */
    @TableField("location_address")
    @ApiModelProperty("仓库地区-具体地址")
    private String locationAddress;

    /**
     * 仓库位置-纬度
     */
    @TableField("location_latitude")
    @ApiModelProperty("仓库位置-纬度")
    private String locationLatitude;

    /**
     * 仓库地区-经度
     */
    @TableField("location_longitude")
    @ApiModelProperty("仓库地区-经度")
    private String locationLongitude;

    /**
     * 排序顺序
     */
    @TableField("sort_order")
    @ApiModelProperty("排序顺序")
    private Integer sortOrder;

    /**
     * 仓库是否启用 ： 0,停用(默认)  1是启用
     */
    @TableField("is_enable")
    @ApiModelProperty("仓库是否启用 ： 0,停用  1是启用(默认)")
    private Integer enable;

    /**
     * 是否删除：0不删(默认)，1删除
     */
    @TableField("is_deleted")
    @ApiModelProperty("是否删除：0不删(默认)，1删除")
    private Integer deleted;

    /**
     * 联系人姓名
     */
    @TableField("contact_name")
    @ApiModelProperty("联系人姓名")
    private String contactName;

    /**
     * 联系人手机号
     */
    @TableField("contact_phone")
    @ApiModelProperty("联系人手机号")
    private String contactPhone;

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
     * 修改日期
     */
    @TableField("update_time")
    @ApiModelProperty("修改日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 用户ID
     */
    @TableField(exist = false)
    @ApiModelProperty("用户ID")
    private String userId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
