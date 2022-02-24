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

import java.io.Serializable;

/**
 * WMS用户-角色关联表
 *
 * @author LiPengFei
 * @date 2021-12-23 13:04:36
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WMS—用户角色关联—实体", description = "描述")
@TableName("wms_user_role")
public class WmsUserRole implements Serializable {
    private static final long serialVersionUID=6841794470752667710L;

    /**
    * 主键ID
    */
    @TableId(value = "user_role_id", type = IdType.INPUT)
    @ApiModelProperty("主键ID")
    private String userRoleId;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField(value = "user_id")
    private String userId;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @TableField(value = "role_name")
    private String roleName;

}
