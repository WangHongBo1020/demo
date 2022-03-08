package com.example.whb_demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/3/8
 * \* Time: 15:22
 * \* Description: wms用户-仓库关联表Dto
 * \
 */
@Data
public class WmsUserStockroomDto implements Serializable {

    /**
     * 主键id
     */
    private String id;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 仓库id
     */
    private String stockroomId;

    /**
     * 仓库组织树id
     */
    private String companyId;

}
