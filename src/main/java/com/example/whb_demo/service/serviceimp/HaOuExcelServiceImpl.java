package com.example.whb_demo.service.serviceimp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.whb_demo.entity.HaOuShippedExcelVo;
import com.example.whb_demo.entity.LedgerDetail;
import com.example.whb_demo.mapper.HaOuExcelMapper;
import com.example.whb_demo.service.HaOuExcelService;
import com.example.whb_demo.utils.ExcelUtil;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class HaOuExcelServiceImpl implements HaOuExcelService {

    @Resource
    private HaOuExcelMapper haOuExcelMapper;

    /**
     * 哈欧数据发运统计数据清洗更新
     * @param file
     * @return
     */
    @Override
    public String updataData(MultipartFile file) throws Exception {
        List<HaOuShippedExcelVo> excelVoList = ExcelUtil.importExcel(file, 0, 1, HaOuShippedExcelVo.class);

        if (excelVoList != null && !excelVoList.isEmpty()){

            int i = 0;
            for (HaOuShippedExcelVo excelVo : excelVoList) {
                log.info("excelVo----BookingNo:{},ContainerNum:{}",excelVo.getBookingNo(),excelVo.getContainerNum());

                if (StringUtils.isNotBlank(excelVo.getBookingNo()) &&
                        StringUtils.isNotBlank(excelVo.getContainerNum()) &&
                        StringUtils.isNotBlank(excelVo.getRate()) /*&&
                        StringUtils.isNotBlank(excelVo.getSingleContainerSubsidyAmount())*/) {

                    int count = haOuExcelMapper.update(null,new LambdaUpdateWrapper<LedgerDetail>()
                            .set(LedgerDetail::getRate,excelVo.getRate())
                            /*.set(LedgerDetail::getSingleContainerSubsidyAmount,excelVo.getSingleContainerSubsidyAmount())*/
                            .eq(LedgerDetail::getBookingNo,excelVo.getBookingNo())
                            .eq(LedgerDetail::getContainerNum,excelVo.getContainerNum()));

                    i +=  count;

                }
            }
            System.out.println(i);
            return "0";
        }

        return null;
    }
}
