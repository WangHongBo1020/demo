package com.example.whb_demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author zpw
 * @date 2021/10/24
 * @Description: wms角色信息
 */
@Setter
@Getter
@TableName("wms_role")
public class WmsRole implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "wms_role_id", type = IdType.INPUT)
    @ApiModelProperty("主键Id")
    private String wmsRoleId;

    /**
     * 角色编码
     */
    @TableField("wms_role_code")
    @ApiModelProperty("角色编码")
    private String wmsRoleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @TableField("wms_role_name")
    private String wmsRoleName;


    /**
     * 主键ID
     */
    @TableField(value = "erp_customer_id")
    @ApiModelProperty("erp系统服务商Id")
    private String erpCustomerId;

    /**
     * 是否为主账号角色 0 否 1 是
     */
    @ApiModelProperty("是否为主账号角色 0 否 1 是")
    @TableField(value = "main")
    private Byte main;

    /**
     * 角色详情
     */
    @ApiModelProperty("角色详情")
    @TableField(value = "role_desc")
    private String roleDesc;

    /**
     * 删除状态 0 未删除、1 已删除
     */
    @TableField("is_deleted")
    @TableLogic(value = "0", delval = "1")
    @ApiModelProperty("删除: 0 未删除、1 已删除")
    private Integer deleted;

    /**
     * 菜单权限字符集
     */
    @TableField(exist = false)
    @ApiModelProperty("菜单权限 字符串使用‘,' `")
    private String menuIds;

    /**
     * 菜单权限列表
     */
    @TableField(exist = false)
    @ApiModelProperty("菜单权限")
    private List<String> menuIdList;

    /**
     * 权限按钮
     */
    @TableField(exist = false)
    private List<String> menuCodeList;
}
