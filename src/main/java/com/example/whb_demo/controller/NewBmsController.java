package com.example.whb_demo.controller;

import com.example.whb_demo.dto.BillingRuleContentRes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * \* User: wanghongbo
 * \* Date: 2023/7/11 09:29
 * \* Description:
 * \
 */
@Slf4j
@RestController
@RequestMapping("/newbms")
public class NewBmsController {


    @PostMapping("/mockBilling")
    public void mockBilling(@RequestBody String jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuffer buffer = new StringBuffer();
        StringBuffer atastBuffer = new StringBuffer();
        try {
            List<BillingRuleContentRes> personList = objectMapper.readValue(jsonObject, new TypeReference<List<BillingRuleContentRes>>() {
            });

            personList.forEach(res -> {

                res.getObj().forEach(o -> {

                    //  判断花括号
                    if (!" } ".equals(o.getCode())){
                        buffer.append(o.getCode()).append(" ");

                    } else {
                        atastBuffer.append(o.getCode()).append(" ");

                    }


                });

                //  判断子级是否为空
                if (res.getChildList() != null && !res.getChildList().isEmpty()) {

                    recursionChildList(res.getChildList(),buffer);


                } else {
                    buffer.append(";").append(" ");
                }
            });

        } catch (Exception e) {
            log.error("计费公式解析失败:{}", e);
        }
        String s = String.valueOf(buffer) + String.valueOf(atastBuffer) + "return obj;";

        System.out.println(s);
    }

    /**
     * 递归循环计费规则childList
     * wanghongbo
     * 20230711
     *
     * @param childList
     * @param buffer
     * @return
     */
    private void recursionChildList(List<BillingRuleContentRes> childList, StringBuffer buffer) {

        StringBuffer buffers = new StringBuffer();
        StringBuffer abbuffer = new StringBuffer();

        //  逻辑处理
        childList.forEach(c -> {
            if (c.getObj() != null && !c.getObj().isEmpty()) {

                c.getObj().forEach(b -> {

                    //  判断花括号
                    if (!" } ".equals(b.getCode())) {
                        buffers.append(b.getCode()).append(" ");

                    } else {
                        abbuffer.append(b.getCode()).append(" ");
                    }
                });
                buffer.append(String.valueOf(buffers));

            }

            if (!CollectionUtils.isEmpty(c.getChildList())) {
                recursionChildList(c.getChildList(), buffer);
            }else {
                buffer.append(";").append(" ");
            }

        });


        buffer.append(String.valueOf(abbuffer));

    }



}
