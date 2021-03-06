package com.example.whb_demo.service.serviceimp;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.whb_demo.dao.ExcelServiceDao;
import com.example.whb_demo.entity.*;
import com.example.whb_demo.mapper.*;
import com.example.whb_demo.service.ExclService;
import com.example.whb_demo.utils.ExcelUtil;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import com.example.whb_demo.vo.WmsUserExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Resource
    private WmsStockroomMapper wmsStockroomMapper;

    @Resource
    private WmsStockroomMemoryMapper wmsStockroomMemoryMapper;
    @Resource
    private WmsStockroomOutDetailsMapper wmsStockroomOutDetailsMapper;

    @Resource
    private WmsStockroomPositionMapper wmsStockroomPositionMapper;

    @Override
    public String insertData(MultipartFile file) throws Exception {

        List<WmsUserExcelVo> excelListl = ExcelUtil.importExcel(file, 0, 1, WmsUserExcelVo.class);
        log.info("excelListl-----:{}", JSONObject.toJSONString(excelListl));

        List<WmsUser> userList = new ArrayList<>();

        List<String> error = new ArrayList<>();
        int index = 1;

        if (excelListl != null && !excelListl.isEmpty()) {

            for (WmsUserExcelVo excelVo : excelListl) {

                index ++;

                if (excelVo != null && excelVo.getUsername() != null){

                    List<String> strerror = excelServiceDao.errorNull(excelVo, index);

                    if (strerror != null && !strerror.isEmpty()) {
                        error.addAll(strerror);
                    }

                    WmsUser wmsUser = excelServiceDao.isNotData(excelVo);

                    if (wmsUser != null) {
                        userList.add(wmsUser);
                    }
                }

            }

            if (!error.isEmpty()) {
                //??????????????????
                return error.stream().map(String::valueOf).collect(Collectors.joining(","));
            }

            if (userList != null && !userList.isEmpty()) {

                List<WmsUser> repetition = excelServiceDao.repetitionRepetition(userList);

                //???wms_user???????????????
                int count = excelMapper.insertuserData(repetition);

                if (count > 0) {

                    // TODO ???wms??????-??????????????????????????????wms??????????????????????????????wms??????-???????????????
                    // TODO wms??????-???????????????????????????

                    String rolecount = excelServiceDao.insertWmsrole(repetition);

                    return !"".equals(rolecount) ? "0" : null;

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

            index++;

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
            //??????????????????
            return error.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        //????????????,??????,?????????????????????????????????
        List<WmsMemoryExcelVo> list = gressionList.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (list != null && !list.isEmpty()){

            for (WmsMemoryExcelVo vo : list) {

                error.add("???????????????" + vo.getStockroomName() + " " +
                        "????????????" + vo.getStockroomPartitionName() + " " +
                        "?????????:" + vo.getStockroomPositionCode() + " " + "???????????????");
            }

        }

        if (!error.isEmpty()) {
            //??????????????????
            return error.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        //???????????????????????????
        List<WmsStockroomMemory>  memoryList = excelServiceDao.correctData(excelListl);
        log.info("memoryList--------:{}", JSONObject.toJSONString(memoryList));

        //??????vin??????????????????
        List<WmsStockroomMemory> repetition = excelServiceDao.repetitionRepetitions(memoryList);
        log.info("repetition-----:{}", JSONObject.toJSONString(repetition));

        for (WmsStockroomMemory memory : repetition) {
            if (memory.getError() != null && !memory.getError().isEmpty()) {
                String memoryListError = memory.getError().stream().map(String::valueOf).collect(Collectors.joining(","));
                error.add(memoryListError);
            }
        }

        if (!error.isEmpty()) {
            //??????????????????
            return error.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        try {

            int count = excelMapper.insertmemoryData(repetition);
            //???????????????????????????
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            wmsStockroomPositionMapper.update(null, new LambdaUpdateWrapper<WmsStockroomPosition>()
                    .set(WmsStockroomPosition::getPositionStatus, 1)
                    .set(WmsStockroomPosition::getUpdateTime, LocalDateTime.now().format(formatter))
                    .in(WmsStockroomPosition::getPositionId, repetition.stream().map(WmsStockroomMemory::getStockroomPositionId).collect(Collectors.toList())));
            return count > 0 ? "0" : null;

        } catch (Exception e) {
            log.info("???????????????????????????????????????:{}", JSONObject.toJSONString(memoryList), e);
        }

        return null;
    }

    @Override
    public String ceshi() {

        WmsStockroom stockroom = wmsStockroomMapper.selectOne(new LambdaQueryWrapper<WmsStockroom>()
                .eq(WmsStockroom::getStockroomCode,1));


        List<WmsStockroomMemory> memory = wmsStockroomMemoryMapper.selectList(new LambdaQueryWrapper<WmsStockroomMemory>()
                .eq(WmsStockroomMemory::getStockroomOutCode, "OUTK13202205170001"));

        List<WmsStockroomOutDetails> details = wmsStockroomOutDetailsMapper.selectList(new LambdaQueryWrapper<WmsStockroomOutDetails>()
                .eq(WmsStockroomOutDetails::getStockroomOutNo, "OUTK13202205170001"));

        for (WmsStockroomMemory men : memory) {

            for (WmsStockroomOutDetails detail : details) {

                if (men.getVin().equals(detail.getVin())){

                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


                    Date smdate = null;
                    Date bdate = null;
                    try {
                        smdate = sdf.parse(sdf.format(detail.getEalityTiime()));
                        bdate =sdf.parse(sdf.format(men.getStockroomInDate()));

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(smdate);

                        long time1 = cal.getTimeInMillis();

                        cal.setTime(bdate);
                        long time2 = cal.getTimeInMillis();
                        long datay=(time1-time2)/(1000*3600*24);

                        int stockroomMemory = wmsStockroomMemoryMapper.update(null,new LambdaUpdateWrapper<WmsStockroomMemory>()
                            .set(WmsStockroomMemory::getStockroomMemoryDays,String.valueOf(datay))
                                .set(WmsStockroomMemory::getStockroomOutDate,detail.getEalityTiime())
                        .eq(WmsStockroomMemory::getVin,men.getVin())
                        .eq(WmsStockroomMemory::getStockroomOutCode,"OUTK13202205170001"));


                        System.out.println(stockroomMemory);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return null;
    }
}
