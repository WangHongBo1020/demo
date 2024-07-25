package com.example.whb_demo.controller;

import com.example.whb_demo.dto.WmsStockroomMemoryDto;
import com.example.whb_demo.dto.WmsUserDto;
import com.example.whb_demo.entity.RateDetailOaStaging;
import com.example.whb_demo.entity.WmsStockroom;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.service.ExclService;
import com.example.whb_demo.utils.Result;
import com.example.whb_demo.vo.RateDetailOaStagingExcelVo;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 10:50
 * \* Description:
 * \
 */
@Slf4j
@RestController
@RequestMapping("/whb")
public class ExcelCcontroller {

    @Resource
    private ExclService exclService;

    @ApiOperation(value = "创建账户")
    @PostMapping("/wmsusers")
    public Result<WmsUserDto, Object> saveUser(@RequestParam("file") @Ignore MultipartFile file) throws Exception {

        Assert.notNull(file,"Eexecl不能为空");
        String result = exclService.insertData(file);

        return "0".equals(result) ? Result.succeed(result) : Result.failed(result);
    }

    @ApiOperation(value = "创建库存")
    @PostMapping("/insertmemory")
    public Result<WmsStockroomMemoryDto, Object> insertmemory(@RequestBody MultipartFile file) throws Exception {

        Assert.notNull(file,"Eexecl不能为空");
        String result = exclService.insertMemoryData(file);

        return "0".equals(result) ? Result.succeed(result) : Result.failed(result);
    }

    @ApiOperation(value = "oa->bms excel")
    @PostMapping("/bmsExcel")
    public Result<RateDetailOaStaging, Object> oaBmsExcel(@RequestParam("file") @Ignore MultipartFile file, HttpServletResponse response) throws Exception {

        Assert.notNull(file,"Eexecl不能为空");
        List<RateDetailOaStagingExcelVo> result = exclService.oaBmsExcel(file,response);

        return "0".equals(result) ? Result.succeed(result) : Result.failed("失败");
    }

}
