package com.example.whb_demo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.whb_demo.entity.WmsRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 11:28
 * \* Description:
 * \
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class WmsUserExcelVo {

    @Excel(name = "登录账户",orderNum = "1", width = 20)
    private String username;

    @Excel(name = "邮箱",orderNum = "2", width = 20)
    private String email;

    @Excel(name = "姓名",orderNum = "3", width = 20)
    private String name;

    @Excel(name = "手机号",orderNum = "4", width = 20)
    private String mobile;

    @Excel(name = "角色名称(多个角色以顿号、分割)",orderNum = "5", width = 20)
    private String role;

}
