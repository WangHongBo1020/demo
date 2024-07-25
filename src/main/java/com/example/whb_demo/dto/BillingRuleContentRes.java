package com.example.whb_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * \* User: wanghongbo
 * \* Date: 2023/7/11 09:24
 * \* Description:
 * \
 */
@Data
public class BillingRuleContentRes implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("obj")
    private List<BillingRuleContent> obj;
    @JsonProperty("childList")
    private List<BillingRuleContentRes> childList;

    @JsonProperty("depth")
    private String depth;

    @JsonProperty("checked")
    private String checked;

}
