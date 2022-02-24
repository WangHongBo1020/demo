package com.example.whb_demo.service.serviceimp;

import com.alibaba.fastjson.JSONObject;
import com.example.whb_demo.dao.ExcelServiceDao;
import com.example.whb_demo.entity.WmsRole;
import com.example.whb_demo.entity.WmsStockroomMemory;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.entity.WmsUserRole;
import com.example.whb_demo.mapper.ExcelMapper;
import com.example.whb_demo.service.ExclService;
import com.example.whb_demo.utils.ExcelUtil;
import com.example.whb_demo.utils.IdGenerator;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import com.example.whb_demo.vo.WmsUserExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 13:43
 * \* Description:
 * \
 */
@Slf4j
@Service
public class ExclServiceImpl implements ExclService {

    @Resource
    private ExcelServiceDao excelServiceDao;

    @Resource
    private ExcelMapper excelMapper;

    @Override
    public String insertData(MultipartFile file) throws Exception {

        List<WmsUserExcelVo> excelListl = ExcelUtil.importExcel(file, 0, 1, WmsUserExcelVo.class);
        log.info("excelListl-----:{}", JSONObject.toJSONString(excelListl));

        List<WmsUserExcelVo> repetition = excelServiceDao.repetitionRepetition(excelListl);

        List<WmsUser> userList = new ArrayList<>();

        List<String> error = new ArrayList<>();
        int index = 1;

        if (repetition != null && !repetition.isEmpty()) {
            for (WmsUserExcelVo excelVo : repetition) {
                index = index + 1;

                List<String> strerror = excelServiceDao.errorNull(excelVo, index);

                if (strerror != null && !strerror.isEmpty()) {
                    error.addAll(strerror);
                }

                WmsUser wmsUser = excelServiceDao.isNotData(excelVo);

                if (wmsUser != null) {
                    userList.add(wmsUser);
                }

            }

            if (!error.isEmpty()) {
                //错误信息返回
                return error.stream().map(String::valueOf).collect(Collectors.joining(","));
            }

            if (userList != null && !userList.isEmpty()) {

                int count = excelMapper.insertuserData(userList);

                if (count > 0) {

                    String rolecount = excelServiceDao.insertWmsrole(userList);

                    if (!"".equals(rolecount)) {
                        return "0";
                    }

                }

            }
        }

        return null;
    }

    /**
     * @param file
     * @return
     */
    @Override
    public String insertMemoryData(MultipartFile file) throws Exception {

        List<WmsMemoryExcelVo> excelListl = ExcelUtil.importExcel(file, 0, 1, WmsMemoryExcelVo.class);
        log.info("excelListl-----:{}", JSONObject.toJSONString(excelListl));

        List<WmsMemoryExcelVo> gressionList = new ArrayList<>();

        List<String> error = new ArrayList<>();

        int index = 1;

        for (WmsMemoryExcelVo excelVo : excelListl) {

            index = index + 1;

            List<String> strerror = excelServiceDao.errorMemoryNull(excelVo, index);

            if (strerror != null && !strerror.isEmpty()) {
                error.addAll(strerror);
            }

            WmsMemoryExcelVo errorPositionCode = new WmsMemoryExcelVo();

            errorPositionCode.setStockroomName(excelVo.getStockroomName());
            errorPositionCode.setStockroomPartitionName(excelVo.getStockroomPartitionName());
            errorPositionCode.setStockroomPositionCode(excelVo.getStockroomPositionCode());
            gressionList.add(errorPositionCode);

        }

        if (!error.isEmpty()) {
            //错误信息返回
            return error.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        //根据仓库,库区,库位查询是否存在重复值
        List<WmsMemoryExcelVo> list = gressionList.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        if (list != null && !list.isEmpty()){

            for (WmsMemoryExcelVo vo : list) {

                error.add("仓库名称：" + vo.getStockroomName() + " " +
                        "库区名称" + vo.getStockroomPartitionName() + " " +
                        "库位号:" + vo.getStockroomPositionCode() + " " + "重复请检查");
            }

        }

        if (!error.isEmpty()) {
            //错误信息返回
            return error.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        //插入库存明细表数据
        List<WmsStockroomMemory>  memoryList = excelServiceDao.correctData(excelListl);
        log.info("memoryList--------:{}", JSONObject.toJSONString(memoryList));

        List<WmsStockroomMemory> repetition = excelServiceDao.repetitionRepetitions(memoryList);
        log.info("repetition-----:{}", JSONObject.toJSONString(excelListl));

        for (WmsStockroomMemory memory : repetition) {
            if (memory.getError() != null && !memory.getError().isEmpty()) {
                String memoryListError = memory.getError().stream().map(String::valueOf).collect(Collectors.joining(","));
                error.add(memoryListError);
            }
        }

        if (!error.isEmpty()) {
            //错误信息返回
            return error.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        try {

            int count = excelMapper.insertmemoryData(memoryList);

            return count > 0 ? "0" : null;

        } catch (Exception e) {
            log.info("批量插入库存明细表数据异常:{}", JSONObject.toJSONString(memoryList), e);
        }

        return null;
    }
}