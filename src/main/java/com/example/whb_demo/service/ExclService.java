package com.example.whb_demo.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 13:37
 * \* Description:
 * \
 */
public interface ExclService {

    /**
     * 批量创建账户
     * @param file
     * @return
     */
    String insertData(MultipartFile file) throws Exception;

    /**
     * 批量创建库存信息数据
     * @param file
     * @return
     */
    String insertMemoryData(MultipartFile file) throws Exception;

}
