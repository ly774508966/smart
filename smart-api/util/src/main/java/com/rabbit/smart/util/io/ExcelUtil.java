package com.rabbit.smart.util.io;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by xuzhang on 2018/5/22.
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 读取excel
     * @param path
     * @return
     * @throws IOException
     */
    public static Workbook readExcel(String path) throws IOException {
        Workbook workbook = null;
        if (path.endsWith("xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
        } else if (path.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
        }
        return workbook;
    }

    /**
     * 读取excel
     *
     * @param path
     * @param index
     * @return
     * @throws IOException
     */
    public static Sheet readSheet(String path, int index) throws IOException {
        Workbook workbook = readExcel(path);
        return workbook == null ? null : workbook.getSheetAt(index);
    }

    /**
     * 获取excel单元格的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }

        if (cell.getCellType() == 0) {
            cell.setCellType(1);
        }

        switch (cell.getCellType()) {
            case 0:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case 1:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case 4:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case 2:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case 3:
                cellValue = "";
                break;
            case 5:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
        }

        return cellValue;
    }
}
