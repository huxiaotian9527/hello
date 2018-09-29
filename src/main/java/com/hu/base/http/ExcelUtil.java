package com.hu.base.http;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类
 * <p>
 * Created by 姚海<hai_yao@kingdee.action> on 2017/6/26 17:31
 * </p>
 */
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    private ExcelUtil() {
    }

    /**
     * 读取Excel数据
     * @param in 输入流
     * @param titleRowNum 表头所在行，数值从0开始
     * @throws IOException
     */
    public static TwoTuple<List<String>, List<List<String>>> readData(InputStream in, Integer titleRowNum) throws IOException {
        Workbook book = buildWorkbook(in);
        // 获取excel第一个sheet
        Sheet sheet = book.getSheetAt(0);

        List<String> titleData = titleRowNum == null ? new ArrayList<String>() : getRowData(sheet, titleRowNum);
        List<List<String>> rowData = getData(sheet, titleRowNum);

		return new TwoTuple<List<String>, List<List<String>>>(titleData, rowData);
    }

    /**
     * 构造Excel对象
     * @param in 输入流
     * @throws IOException
     */
    private static Workbook buildWorkbook(InputStream in) throws IOException {
        Workbook book;
        try {
            book = new XSSFWorkbook(in);
        } catch (Exception ex) {
            LOGGER.error("构造XSSFWorkbook对象失败，转为HSSFWorkbook对象 error={}", ex.getMessage());
            book = new HSSFWorkbook(in);
        }
        return book;
    }

    /**
     * 取到全部行数据
     * @param sheet 一个表格
     * @param titleRowNum 表头所在行，数值从0开始
     */
    private static List<List<String>> getData(Sheet sheet, Integer titleRowNum) {
		List<List<String>> data = new ArrayList<List<String>>();

        int rows = sheet.getLastRowNum() + 1;
        // 如果没有表头，数据行从1行开始遍历（数值为0），否则从表头的下一行开始遍历
        int startRow = titleRowNum == null ? 0 : titleRowNum + 1;
        // 遍历行
        for (int icount = startRow; icount < rows; icount++) {
            data.add(getRowData(sheet, icount));
        }

        return data;
    }

    /**
     * 取到单行数据
     * @param sheet 一个表格
     * @param rowNum 行数
     */
    private static List<String> getRowData(Sheet sheet, int rowNum) {
		List<String> rowData = new ArrayList<String>();

        // 单元格
        Cell cell;

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return rowData;
        }
        int line = row.getLastCellNum();
        // 空行不处理
        if (isEmptyByRow(row)) {
            return rowData;
        }

        // 遍历列
        for (int j = 0; j < line; j++) {
            cell = row.getCell(j);
            // 空跳过 有些字段是允许为空的
            if (cell == null) {
                rowData.add("");
                continue;
            }
            // 设置字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            String cellValue = cell.getStringCellValue().replaceAll("(\r\n|\r|\n|\n\r)", " ").trim();
            if(StringUtils.isBlank(cellValue)){
                rowData.add("");
            } else {
                rowData.add(cellValue);
            }
        }

        return rowData;
    }

    /**
     * 判断是否是空行 全部为空 为空行
     */
    private static boolean isEmptyByRow(Row row) {
        int cellCount = row.getLastCellNum();
        Cell cell;
        int emptyCellCount = 0;
        for (int j = 0; j < cellCount; j++) {
            cell = row.getCell(j);
            if (cell == null) {
                emptyCellCount++;
                continue;
            }
            // 设置字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            if (StringUtils.isBlank(cell.getStringCellValue())) {
                emptyCellCount++;
            }
        }
        return cellCount == emptyCellCount;
    }

}
