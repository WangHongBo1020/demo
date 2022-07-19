package com.example.whb_demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface HaOuExcelService {

    /**
     * 哈欧数据发运统计数据清洗更新
     * @param file
     * @return
     */
    String updataData(MultipartFile file) throws Exception;
}
