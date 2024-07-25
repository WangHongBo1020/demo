package com.example.whb_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * \* User: wanghongbo
 * \* Date: 2023/7/11 08:49
 * \* Description:
 * \
 */
@Data
public class BillingRuleContent implements Serializable {
    private static final long serialVersionUID =1L;

    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;


}
