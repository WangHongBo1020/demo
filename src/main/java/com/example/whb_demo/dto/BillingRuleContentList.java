package com.example.whb_demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * \* User: wanghongbo
 * \* Date: 2023/7/23 15:02
 * \* Description:
 * \
 */
@Data
public class BillingRuleContentList implements Serializable {
    private static final long serialVersionUID = 1L;

   private BillingRuleContentRes  res;
}
