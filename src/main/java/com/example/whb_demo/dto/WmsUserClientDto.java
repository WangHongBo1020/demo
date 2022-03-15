package com.example.whb_demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/3/8
 * \* Time: 15:58
 * \* Description:
 * \
 */
@Data
public class WmsUserClientDto implements Serializable {
    
    /**
     * 主键ID
     */
    private String userClientId;
    
    /**
     *租户ID
     */
    private String tenantId;
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 客户ID
     */
    private String clienteleId;
    
    /**
     * 客户名称
     */
    private String clienteleName;
    
    /**
     *删除：0 未删除；1 已删除
     */
    private String isDeleted;
    
    /**
     * 创建人
     */
    private String createUser;
    
    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
