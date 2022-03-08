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
@ApiModel(value = "客户信息——实体", description = "描述")
@TableName("wms_client_information")
public class WmsClientInformation {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	//@TableId("clientele_id" ,type = IdType.INPUT)
	@TableId(value = "clientele_id", type = IdType.INPUT)
	@ApiModelProperty("主键ID")
	private String clienteleId;

	/**
	 * 租户ID
	 */
	@TableField("tenant_id")
	@ApiModelProperty("租户ID")
	private String tenantId;
	/**
	 * 用户ID
	 */
	@TableField("user_id")
	@ApiModelProperty("用户ID")
	private String userId;

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
	 * 客户别名
	 */
	@TableField("clientele_alias")
	@ApiModelProperty("客户别名")
	private String clienteleAlias;

	/**
	 * 客户分类
	 */
	@TableField("clientele_category")
	@ApiModelProperty("客户分类")
	private Integer clienteleCategory;

	/**
	 * 关联省ID
	 */
	@TableField("province_id")
	@ApiModelProperty("关联省ID")
	private String provinceId;

	/**
	 * 关联城市ID
	 */
	@TableField("city_id")
	@ApiModelProperty("关联城市ID")
	private String cityId;

	/**
	 * 关联区ID
	 */
	@TableField("area_id")
	@ApiModelProperty("关联区ID")
	private String areaId;

	/**
	 * 详细地址
	 */
	@TableField("detailed_address")
	@ApiModelProperty("详细地址")
	private String detailedAddress;

	/**
	 * 法定代表人
	 */
	@TableField("legal_person")
	@ApiModelProperty("法定代表人")
	private String legalPerson;

	/**
	 * 统一社会信用代码
	 */
	@TableField("credit_code")
	@ApiModelProperty("统一社会信用代码")
	private String creditCode;

	/**
	 * 联系人
	 */
	@TableField("contact")
	@ApiModelProperty("联系人")
	private String contact;

	/**
	 * 电话
	 */
	@TableField("phone")
	@ApiModelProperty("电话")
	private String phone;

	/**
	 * 注册地址
	 */
	@TableField("registeredregistered_address")
	@ApiModelProperty("注册地址")
	private String registeredregisteredAddress;

	/**
	 * 办公地址
	 */
	@TableField("office_address")
	@ApiModelProperty("办公地址")
	private String officeAddress;

	/**
	 * 传真
	 */
	@TableField("faxes")
	@ApiModelProperty("传真")
	private String faxes;

	/**
	 * 邮箱
	 */
	@TableField("email")
	@ApiModelProperty("邮箱")
	private String email;

	/**
	 * 开户银行
	 */
	@TableField("deposit_bank")
	@ApiModelProperty("开户银行")
	private String depositBank;

	/**
	 * 银行账号
	 */
	@TableField("bank_account")
	@ApiModelProperty("银行账号")
	private String bankAccount;

	/**
	 * 启动状态 停用：0启动：1
	 */
	@TableField("start_status")
	@ApiModelProperty("启动状态 停用：0启动：1")
	private Integer startStatus;

	/**
	 * 营业执照
	 */
	@TableField("business_license")
	@ApiModelProperty("营业执照")
	private String businessLicense;

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
