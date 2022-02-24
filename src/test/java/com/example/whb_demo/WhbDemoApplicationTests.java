package com.example.whb_demo;

import com.alibaba.fastjson.JSONObject;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.utils.Base64Utils;
import com.example.whb_demo.utils.Md5Util;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class WhbDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void Base() {
        String password = Md5Util.getMD5("cjkj123456");
        System.out.println(password);

        String passwords =Md5Util.getMD5(password);

        System.out.println(passwords);
    }

    @Test
    public void ArraysasList() {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        String role = "开发,测试,产品";
        String str[] = role.split(",");
        list1.add(role);
        list = Arrays.asList(str);

        for (String s : list1) {
            String ss = s;
            System.out.println(ss);
        }

        for (String s : list) {
            String sss = s;
            System.out.println(sss);
        }

        System.out.println(list1);
        System.out.println(list);
    }

    @Test
    public void  userListstream(){

        WmsMemoryExcelVo wmsMemoryExcelVo = new WmsMemoryExcelVo();
        wmsMemoryExcelVo.setVin("111");
        wmsMemoryExcelVo.setBrandName("1");
        WmsMemoryExcelVo wmsMemoryExcelVo1 = new WmsMemoryExcelVo();
        WmsMemoryExcelVo wmsMemoryExcelVo3 = new WmsMemoryExcelVo();
        wmsMemoryExcelVo1.setVin("111");
        wmsMemoryExcelVo1.setBrandName("2");

        wmsMemoryExcelVo3.setVin("1111");
        wmsMemoryExcelVo3.setBrandName("22");
        List<WmsMemoryExcelVo> userList = new ArrayList<>();

        userList.add(wmsMemoryExcelVo1);
        userList.add(wmsMemoryExcelVo);
        userList.add(wmsMemoryExcelVo3);

        //对list数据 根据某个字段去掉重复数据
        List<WmsMemoryExcelVo> userLists = userList.stream().
                collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(WmsMemoryExcelVo::getVin))),
                        ArrayList::new));

        WmsMemoryExcelVo wmsMemoryExcelVo2 = new WmsMemoryExcelVo();
        for (WmsMemoryExcelVo list : userLists) {
            wmsMemoryExcelVo2.setVin(list.getVin());
            wmsMemoryExcelVo2.setBrandName(list.getBrandName());
        }

        System.out.println(JSONObject.toJSONString(wmsMemoryExcelVo2));
        System.out.println(JSONObject.toJSONString(userLists));

    }

    @Test
    public void getDuplicateElements() {

        WmsMemoryExcelVo wmsMemoryExcelVo = new WmsMemoryExcelVo();
        wmsMemoryExcelVo.setVin("111");
        wmsMemoryExcelVo.setBrandName("1");
        wmsMemoryExcelVo.setClienteleType("2");

        WmsMemoryExcelVo wmsMemoryExcelVo1 = new WmsMemoryExcelVo();
        wmsMemoryExcelVo1.setVin("111");
        wmsMemoryExcelVo1.setBrandName("1");
        wmsMemoryExcelVo1.setClienteleType("1");

        List<WmsMemoryExcelVo> userList = new ArrayList<>();

        userList.add(wmsMemoryExcelVo1);
        userList.add(wmsMemoryExcelVo);

        List list1 = userList.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(list1));
    }

}
