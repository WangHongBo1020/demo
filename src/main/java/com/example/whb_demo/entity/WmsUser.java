package com.example.whb_demo.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.example.whb_demo.utils.DateToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zpw
 * @date 2021/10/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wms_user")
public class WmsUser implements Serializable {

    private static final long serialVersionUID = 1699796877287555496L;

    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty("主键ID")
    private String userId;

    @TableField(value = "erp_customer_id")
    @ApiModelProperty("erp系统服务商Id")
    private String erpCustomerId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String name;

    @TableField("head_img_url_id")
    @ApiModelProperty("头像地址(图片服务id)")
    private String headImgUrlId;

    @ApiModelProperty("账号激活状态：0 禁用；1 启用")
    private Boolean enabled;

    @ApiModelProperty("删除状态：0 未删除；1 已删除")
    private Integer deleted;


    @TableField("tenant_id")
    @ApiModelProperty("租户id")
    private String tenantId;

    @TableField("id_number")
    @ApiModelProperty("身份证号")
    private String idNumber;

    @TableField("card_name")
    @ApiModelProperty("身份证姓名")
    private String cardName;

    @ApiModelProperty("是否主账号 0 否 1是")
    @TableField("main")
    private Integer main;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    @ApiModelProperty("创建人姓名")
    @TableField(value = "create_user")
    private String createUser;

    @ApiModelProperty("修改人姓名")
    @TableField(value = "update_user")
    private String updateUser;


    @TableField(value = "role_id")
    @ApiModelProperty("角色ID")
    private String roleId;

    private String wmsRoleId;

    @TableField(exist = false)
    @ApiModelProperty("角色")
    private String role;

    @TableField(exist = false)
    @ApiModelProperty("仓库编码")
    private String stockroomCode;

    @TableField(exist = false)
    @ApiModelProperty("客户编码")
    private String clienteleCode;

    @TableField(exist = false)
    @ApiModelProperty("角色List")
    private List<String> roleList;

}
