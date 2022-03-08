package com.example.whb_demo.mapper;

import com.example.whb_demo.dto.WmsUserClientDto;
import com.example.whb_demo.dto.WmsUserStockroomDto;
import com.example.whb_demo.entity.*;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 15:24
 * \* Description:
 * \
 */
@Mapper
public interface ExcelMapper {

    int insertuserData(@Param("list") List<WmsUser> userList);

    int insertWmsrole(@Param("vo") WmsRole role);

    int insertWmsuserrole(@Param("vo") WmsUserRole userRole);

    String getWmsRoleId(@Param("name") String s);

    String getCompanyId(@Param("name") String companyName);

    WmsStockroomMemory getStockroomidandcode(@Param("vo") WmsMemoryExcelVo vo);

    WmsStockroomMemory getClienteleBrandid(@Param("vo") WmsMemoryExcelVo excelVo);

    int insertmemoryData(@Param("list") List<WmsStockroomMemory> memoryList);

    WmsStockroomMemory getPartitionidData(@Param("vo") WmsMemoryExcelVo excelVo);

    WmsStockroom queryStockroom(@Param("code") String code);

    int insertWmsUserStockroom(@Param("list") List<WmsUserStockroomDto> dtoList);

    WmsUserClientDto queryClient(@Param("code") String s);

    int insertWmsUserClient(@Param("list") List<WmsUserClientDto> dtoList);
}
