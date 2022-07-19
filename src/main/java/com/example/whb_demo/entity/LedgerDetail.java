package com.example.whb_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 台账明细表
 * </p>
 *
 * @author tianjie
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("f_ledger_detail")
@ApiModel(value="LedgerDetail对象", description="台账明细表")
public class LedgerDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "虚拟箱号")
    private String containerNo;

    @ApiModelProperty(value = "集装箱号")
    private String containerNum;

    @ApiModelProperty(value = "订舱编号")
    private String bookingNo;

    @ApiModelProperty(value = "线路类型")
    private String lineType;

    @ApiModelProperty(value = "进出口")
    private String importOrExport;

    @ApiModelProperty(value = "实际收货公司")
    private String receivingCompanyName;

    @ApiModelProperty(value = "受理号")
    private String acceptanceNum;

    @ApiModelProperty(value = "箱型")
    private String containerType;

    @ApiModelProperty(value = "班列日期")
    private Long trainsTime;

    @ApiModelProperty(value = "订舱公司(客户)")
    private String customerName;

    @ApiModelProperty(value = "口岸站")
    private String seaport;

    @ApiModelProperty(value = "班列汇率")
    private String rate;

    @ApiModelProperty(value = "报关票数")
    private Long hsTicketNum;

    @ApiModelProperty(value = "单箱总报价")
    private BigDecimal singleContainerTatolAmount;

    @ApiModelProperty(value = "单箱补贴")
    private BigDecimal singleContainerSubsidyAmount;

    @ApiModelProperty(value = "单箱利润（不算政府补贴）")
    private BigDecimal singleContainerProfitNoSubsidy;

    @ApiModelProperty(value = "单箱利润（算政府补贴）")
    private BigDecimal singleContainerProfitIncludeSubsidy;

    @ApiModelProperty(value = "单箱总成本")
    private BigDecimal singleContainerTatolCost;

    @ApiModelProperty(value = "单箱总收入")
    private BigDecimal singleContainerTatolIncome;

    @ApiModelProperty(value = "应收额外费用")
    private BigDecimal incomeExtraExpense;

    @ApiModelProperty(value = "发车日期")
    private Long departureDate;

    @ApiModelProperty(value = "服务类型名称 站到站=CTC  ; 门到门=DTD  ; 站到门=CTD  ; 门到站= DTC")
    private String serverTypeName;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "货值")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "货值币种")
    private String goodsPriceCurrency;

    @ApiModelProperty(value = "货重（kg）")
    private String goodsWeight;

    @ApiModelProperty(value = "末端终到站")
    private String tailEndStation;

    @ApiModelProperty(value = "起始站站编")
    private String startStationCode;

    @ApiModelProperty(value = "起始站")
    private String startStationName;

    @ApiModelProperty(value = "目的站站编")
    private String endStationCode;

    @ApiModelProperty(value = "目的站")
    private String endStationName;

    @ApiModelProperty(value = "品类")
    private String productName;

    @ApiModelProperty(value = "品名")
    private String goodsName;

    @ApiModelProperty(value = "前端提货线路")
    private String frontPickUpLine;

    @ApiModelProperty(value = "前端提货运输方式")
    private String frontPickUpTransport;

    @ApiModelProperty(value = "站到站报价(美元）")
    private BigDecimal dollarOffer;

    @ApiModelProperty(value = "中欧班列")
    private String chinaEuropeTrain;

    @ApiModelProperty(value = "国内铁路运费收入费用")
    private BigDecimal inlandRailwayIncome;

    @ApiModelProperty(value = "国内铁路运费成本费用")
    private BigDecimal inlandRailwayCost;

    @ApiModelProperty(value = "国内铁路运费供应商")
    private String inlandRailwaySupplierName;

    @ApiModelProperty(value = "国内铁路运费审核日期")
    private Long inlandRailwayAuditTime;

    @ApiModelProperty(value = "国外铁路运费收入费用")
    private BigDecimal foreignRailwayIncome;

    @ApiModelProperty(value = "国外铁路运费成本费用")
    private BigDecimal foreignRailwayCost;

    @ApiModelProperty(value = "国外铁路运费供应商")
    private String foreignRailwaySupplierName;

    @ApiModelProperty(value = "国外铁路运费审核日期")
    private Long foreignRailwayAuditTime;

    @ApiModelProperty(value = "箱使费收入费用")
    private BigDecimal containerIncome;

    @ApiModelProperty(value = "箱使费成本费用")
    private BigDecimal containerCost;

    @ApiModelProperty(value = "箱使费供应商")
    private String containerSupplierName;

    @ApiModelProperty(value = "箱使费审核日期")
    private Long containerAuditTime;

    @ApiModelProperty(value = "排空费收入费用")
    private BigDecimal evacuationIncome;

    @ApiModelProperty(value = "排空费成本费用")
    private BigDecimal evacuationCost;

    @ApiModelProperty(value = "排空费供应商")
    private String evacuationSupplierName;

    @ApiModelProperty(value = "排空费审核日期")
    private Long evacuationAuditTime;

    @ApiModelProperty(value = "前端拖车费收入费用")
    private BigDecimal frontTrailerIncome;

    @ApiModelProperty(value = "前端拖车费成本费用")
    private BigDecimal frontTrailerCost;

    @ApiModelProperty(value = "前端拖车费供应商")
    private String frontTrailerSupplierName;

    @ApiModelProperty(value = "前端拖车费审核日期")
    private Long frontTrailerAuditTime;

    @ApiModelProperty(value = "末端拖车费收入费用")
    private BigDecimal endTrailerIncome;

    @ApiModelProperty(value = "末端拖车费成本费用")
    private BigDecimal endTrailerCost;

    @ApiModelProperty(value = "末端拖车费供应商")
    private String endTrailerSupplierName;

    @ApiModelProperty(value = "末端拖车费审核日期")
    private Long endTrailerAuditTime;

    @ApiModelProperty(value = "报关代理费收入费用")
    private BigDecimal customsIncome;

    @ApiModelProperty(value = "报关代理费成本费用")
    private BigDecimal customsCost;

    @ApiModelProperty(value = "报关代理费供应商")
    private String customsSupplierName;

    @ApiModelProperty(value = "报关代理费审核日期")
    private Long customsAuditTime;

    @ApiModelProperty(value = "运费险收入费用")
    private BigDecimal freightInsuranceIncome;

    @ApiModelProperty(value = "运费险成本费用")
    private BigDecimal freightInsuranceCost;

    @ApiModelProperty(value = "运费险供应商")
    private String freightInsuranceSupplierName;

    @ApiModelProperty(value = "运费险审核日期")
    private Long freightInsuranceAuditTime;

    @ApiModelProperty(value = "买箱费收入费用")
    private BigDecimal caseCosIncome;

    @ApiModelProperty(value = "买箱费成本费用")
    private BigDecimal caseCosCost;

    @ApiModelProperty(value = "买箱费供应商")
    private String caseCosSupplierName;

    @ApiModelProperty(value = "买箱费审核日期")
    private Long caseCosAuditTime;

    @ApiModelProperty(value = "熏蒸费收入费用")
    private BigDecimal fumigationIncome;

    @ApiModelProperty(value = "熏蒸费成本费用")
    private BigDecimal fumigationCost;

    @ApiModelProperty(value = "熏蒸费供应商")
    private String fumigationSupplierName;

    @ApiModelProperty(value = "熏蒸费审核日期")
    private Long fumigationAuditTime;

    @ApiModelProperty(value = "通关一体化费用收入费用")
    private BigDecimal clearanceUnifyIncome;

    @ApiModelProperty(value = "通关一体化费用成本费用")
    private BigDecimal clearanceUnifyCost;

    @ApiModelProperty(value = "通关一体化费用供应商")
    private String clearanceUnifySupplierName;

    @ApiModelProperty(value = "通关一体化费用审核日期")
    private Long clearanceUnifyAuditTime;

    @ApiModelProperty(value = "口岸清关费用收入费用")
    private BigDecimal portCustomsClearanceIncome;

    @ApiModelProperty(value = "口岸清关费用成本费用")
    private BigDecimal portCustomsClearanceCost;

    @ApiModelProperty(value = "口岸清关费用供应商")
    private String portCustomsClearanceSupplierName;

    @ApiModelProperty(value = "口岸清关费用审核日期")
    private Long portCustomsClearanceAuditTime;

    @ApiModelProperty(value = "转关费用收入费用")
    private BigDecimal customsTransferIncome;

    @ApiModelProperty(value = "转关费用成本费用")
    private BigDecimal customsTransferCost;

    @ApiModelProperty(value = "转关费用供应商")
    private String customsTransferSupplierName;

    @ApiModelProperty(value = "转关费用审核日期")
    private Long customsTransferAuditTime;

    @ApiModelProperty(value = "拆箱费收入费用")
    private BigDecimal unpackingIncome;

    @ApiModelProperty(value = "拆箱费成本费用")
    private BigDecimal unpackingCost;

    @ApiModelProperty(value = "拆箱费供应商")
    private String unpackingSupplierName;

    @ApiModelProperty(value = "拆箱费审核日期")
    private Long unpackingAuditTime;

    @ApiModelProperty(value = "装箱费收入费用")
    private BigDecimal packingChargeIncome;

    @ApiModelProperty(value = "装箱费成本费用")
    private BigDecimal packingChargeCost;

    @ApiModelProperty(value = "装箱费供应商")
    private String packingChargeSupplierName;

    @ApiModelProperty(value = "装箱费审核日期")
    private Long packingChargeAuditTime;

    @ApiModelProperty(value = "加固费收入费用")
    private BigDecimal reinforcementIncome;

    @ApiModelProperty(value = "加固费成本费用")
    private BigDecimal reinforcementCost;

    @ApiModelProperty(value = "加固费供应商")
    private String reinforcementSupplierName;

    @ApiModelProperty(value = "加固费审核日期")
    private Long reinforcementAuditTime;

    @ApiModelProperty(value = "材料费收入费用")
    private BigDecimal materialIncome;

    @ApiModelProperty(value = "材料费成本费用")
    private BigDecimal materialCost;

    @ApiModelProperty(value = "材料费供应商")
    private String materialSupplierName;

    @ApiModelProperty(value = "材料费审核日期")
    private Long materialAuditTime;

    @ApiModelProperty(value = "包装费收入费用")
    private BigDecimal packingIncome;

    @ApiModelProperty(value = "包装费成本费用")
    private BigDecimal packingCost;

    @ApiModelProperty(value = "包装费供应商")
    private String packingSupplierName;

    @ApiModelProperty(value = "包装费审核日期")
    private Long packingAuditTime;

    @ApiModelProperty(value = "人民币手续费收入费用")
    private BigDecimal otherIncome;

    @ApiModelProperty(value = "人民币手续费成本费用")
    private BigDecimal otherCost;

    @ApiModelProperty(value = "人民币手续费供应商")
    private String otherSupplierName;

    @ApiModelProperty(value = "人民币手续费审核日期")
    private Long otherAuditTime;

    @ApiModelProperty(value = "货源类型")
    private String goodsSourceType;


}
