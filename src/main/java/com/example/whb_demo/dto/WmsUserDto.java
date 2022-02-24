package com.example.whb_demo.dto;


import com.example.whb_demo.entity.WmsRole;
import com.example.whb_demo.utils.DateToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zpw
 * @date 2021/10/24
 */
@Data
public class WmsUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private String userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("角色名称")
    private String wmsRoleName;


    @ApiModelProperty("主键")
    private String stockroomId;

    @ApiModelProperty("仓库编码")
    private String stockroomCode;

    @ApiModelProperty("仓库名称")
    private String stockroomName;


    @ApiModelProperty("创建时间")
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    @ApiModelProperty("创建人名称")
    private String createUser;

    @ApiModelProperty("更新人名称")
    private String updateUser;


    @ApiModelProperty("头像地址(图片服务id)")
    private String headImgUrlId;

    @ApiModelProperty("账号激活状态：0 禁用；1 启用")
    private Boolean enabled;


    @ApiModelProperty("角色")
    private WmsRole role;

}
