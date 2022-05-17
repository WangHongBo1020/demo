package com.example.whb_demo.controller;

import com.example.whb_demo.service.ExclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/5/11
 * \* Time: 8:56
 * \* Description:
 * \
 */

@Slf4j
@RestController
@RequestMapping("/wms/stockroomIn")
public class TestController {

    @Value("${android.url}")
    private String versionName;

    @Resource
    private ExclService exclService;

    @GetMapping(value = "/sss")
    public String testceshi(){

        String versionNames = versionName;
        System.out.println(versionNames);
        String sss =exclService.ceshi();
        return "";
    }
}
