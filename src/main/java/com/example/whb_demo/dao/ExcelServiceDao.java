package com.example.whb_demo.dao;

import com.example.whb_demo.entity.WmsStockroomMemory;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import com.example.whb_demo.vo.WmsUserExcelVo;

import java.text.ParseException;
import java.util.List;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 13:53
 * \* Description:
 * \
 */
public interface ExcelServiceDao {

    List<WmsUserExcelVo> repetitionRepetition(List<WmsUserExcelVo> excelListl);

    List<String> errorNull(WmsUserExcelVo excelVo, int index);

    WmsUser isNotData(WmsUserExcelVo excelVo);

    String insertWmsrole(List<WmsUser> userList);

    List<WmsStockroomMemory> repetitionRepetitions(List<WmsStockroomMemory> excelListl);

    List<String> errorMemoryNull(WmsMemoryExcelVo excelVo, int index);

    List<WmsStockroomMemory> correctData(List<WmsMemoryExcelVo> excelVo) throws Exception;
}
