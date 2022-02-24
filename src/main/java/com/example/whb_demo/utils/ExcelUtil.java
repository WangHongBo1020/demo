package com.example.whb_demo.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 *
 * @author zpw
 * @date 2021/10/24
 */
public class ExcelUtil {
    private ExcelUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 导出
     *
     * @param list           数据列表
     * @param title          标题
     * @param sheetName      sheet名称
     * @param pojoClass      元素类型
     * @param fileName       文件名
     * @param isCreateHeader 是否创建列头
     * @param response
     * @throws IOException
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName
            , boolean isCreateHeader, HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * 导出
     *
     * @param list      数据列表
     * @param title     标题
     * @param sheetName sheet名称
     * @param pojoClass 元素类型
     * @param fileName  文件名
     * @param response
     * @throws IOException
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName
            , HttpServletResponse response) throws IOException {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName, ExcelType.XSSF));
    }

    /**
     * 导出
     *
     * @param list     数据列表(元素是Map)
     * @param fileName 文件名
     * @param response
     * @throws IOException
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName
            , HttpServletResponse response, ExportParams exportParams) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.XSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        workbook.write(response.getOutputStream());
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return Collections.emptyList();
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws Exception {
        if (file == null) {
            return Collections.emptyList();
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        return ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
    }

    public static ResponseEntity<byte[]> exportExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        //Workbook workbook = new SXSSFWorkbook();
        entity.setType(ExcelType.XSSF);
        new ExcelExportService().createSheet(workbook, entity, pojoClass, dataSet);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        String fileName = System.currentTimeMillis() + ".xlsx";
        workbook.write(stream);
        String codedFileName = URLEncoder.encode(fileName, "UTF-8");
        // 设置HttpHeaders，使浏览器响应下载请求
        HttpHeaders respHeaders = new HttpHeaders();
        // 设置响应格式
        respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 响应文件大小
        respHeaders.setContentLength(stream.toByteArray().length);
        // 设置响应文件，并将中文文件名进行UTF8编码，从而解决乱码
        respHeaders.setContentDispositionFormData("attachment", codedFileName);
        // 将文件以二进制格式返回
        return new ResponseEntity<>(stream.toByteArray(), respHeaders, HttpStatus.OK);
    }


    /**
     * <p>动态添加excel文件的数据验证，现在支持序列</p>
     *
     * @param wb 表格文件 需要为XSSFWorkbook .xlsx
     * @param titleName 需要加动态验证的表头名称
     * @param optionsList 数据验证序列列表
     * @param isRequired 是否必填 true 为必填 false 为非必填
     */
    public static void generateRangeList(Workbook wb, String titleName, List<String> optionsList,boolean isRequired) {
        XSSFSheet sheet =(XSSFSheet) wb.getSheet("Sheet0");
        //获取表头
        Row TitleRow = sheet.getRow(0);
        // 总列数
        int numberOfCells = TitleRow.getPhysicalNumberOfCells();
        int column = -1;
        // 头信息（标题）
        for (int j = 0; j < numberOfCells; j++) {
            Cell cell = TitleRow.getCell(j);
            Asserts.assertTrue(org.apache.commons.lang3.StringUtils.isNotBlank(cell.getStringCellValue()), "没有获取到excel标题!");
            if(titleName.equals(cell.getStringCellValue())){
                if(isRequired){
                    CellStyle style = wb.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.RED.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    //设置水平对齐的样式为居中对齐;
                    style.setAlignment(HorizontalAlignment.CENTER);
                    //设置垂直对齐的样式为居中对齐;
                    style.setVerticalAlignment(VerticalAlignment.CENTER);
                    cell.setCellStyle(style);
                }
                column = j;
                break;
            };
        }
        if (column==-1){
            return;
        }
        String colStr = getExcelColumnLabel(column);
        int size = CollectionUtils.isEmpty(optionsList)?0:optionsList.size();
        String[] options = optionsList.toArray(new String[size]);
        if (options.length == 0){
            return;
        }
        if(String.join(",",options).length()>255){
            //获取所有sheet页个数
            int sheetTotal = wb.getNumberOfSheets();
            String hiddenSheetName = "hiddenSheet" + sheetTotal;
            XSSFSheet hiddenSheet = (XSSFSheet)wb.createSheet(hiddenSheetName);
            Row row;
            //写入下拉数据到新的sheet页中
            for (int i = 0; i < options.length; i++) {
                row = hiddenSheet.createRow(i);
                Cell cell = row.createCell(column);
                cell.setCellValue(options[i]);
            }
            //获取新sheet页内容
            String strFormula = hiddenSheetName + "!$"+colStr+"$1:$"+colStr+"$65535";
            XSSFDataValidationConstraint constraint = new XSSFDataValidationConstraint(DataValidationConstraint.ValidationType.LIST,strFormula);
            // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
            CellRangeAddressList regions = new CellRangeAddressList(1,65535, column, column);
            // 数据有效性对象
            DataValidationHelper help = new XSSFDataValidationHelper((XSSFSheet) sheet);
            DataValidation validation = help.createValidation(constraint, regions);
            sheet.addValidationData(validation);
            //将新建的sheet页隐藏掉
            wb.setSheetHidden(sheetTotal, true);
        }else{
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                    .createExplicitListConstraint(options);
            CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, column, column);
            XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            sheet.addValidationData(validation);
        }
    }

    /**
     * 通过索引获取Excel其对应列的字母
     *
     * @param num
     * @return
     */
    public static String getExcelColumnLabel(int num) {
        String temp = "";
        double i = Math.floor(Math.log(25.0 * (num) / 26.0 + 1) / Math.log(26)) + 1;
        if (i > 1) {
            double sub = num - 26 * (Math.pow(26, i - 1) - 1) / 25;
            for (double j = i; j > 0; j--) {
                temp = temp + (char) (sub / Math.pow(26, j - 1) + 65);
                sub = sub % Math.pow(26, j - 1);
            }
        } else {
            temp = temp + (char) (num + 65);
        }
        return temp;
    }

}
