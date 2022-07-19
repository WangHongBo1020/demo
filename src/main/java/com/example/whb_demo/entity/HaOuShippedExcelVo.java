package com.example.whb_demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class HaOuShippedExcelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "集装箱号", orderNum = "1", width = 20)
    private String containerNum;

    @Excel(name = "订舱编号", orderNum = "2", width = 20)
    private String bookingNo;

    @Excel(name = "班列汇率", orderNum = "3", width = 20)
    private String rate;

    @Excel(name = "单箱补贴", orderNum = "4", width = 20)
    private String singleContainerSubsidyAmount;

}
