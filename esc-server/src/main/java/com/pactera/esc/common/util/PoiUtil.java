package com.pactera.esc.common.util;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.exception.BaseException;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陸文
 * @title: poiUtil
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/4/229:17
 */
public class PoiUtil {

    public static List<JSONArray> readExcel(@RequestParam("file") MultipartFile file) throws BaseException {
        String type = (file.getOriginalFilename()).substring((file.getOriginalFilename()).lastIndexOf("."));
        if (!".xlsx".equals(type) && !".xls".equals(type)) {
            throw new BaseException(BaseErrorCode.TYPE_ERROR.getCode(),
                BaseErrorCode.TYPE_ERROR.getMessage(), "文件格式错误！");
        }
        List<JSONArray> list = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //同时支持Excel 2003、2007
            // File excelFile = new File(url);
            //创建文件对象
            InputStream is = file.getInputStream();
            //文件流
            Workbook workbook = WorkbookFactory.create(is);
            //这种方式 Excel 2003/2007/2010 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets();
            //Sheet的数量
            //遍历每个Sheet
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getLastRowNum() + 1;
                //获取总行数
                //遍历每一行
                for (int r = 1; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
                    if (row == null) {
                        break;
                    }
                    int cellCount = row.getLastCellNum();
                    //获取总列数
                    JSONArray model = new JSONArray();
                    //遍历每一列
                    for (int c = 0; c < cellCount; c++) {
                        Cell cell = row.getCell(c);
                        if (cell != null) {
                            int cellType = cell.getCellType();
                            //String cellValue;
                            if(c == 0 &&cellType == Cell.CELL_TYPE_BLANK){
                                break;
                            }
                            switch (cellType) {
                                case Cell.CELL_TYPE_STRING:
                                    //文本
                                    model.add(cell.getStringCellValue().trim());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    //数字、日期
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        model.add(fmt.format(cell.getDateCellValue()).trim());
                                        //日期型
                                    } else {
                                        model.add(String.valueOf(df.format(cell.getNumericCellValue())).trim());
                                        //数字
                                    }
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    //布尔型
                                    model.add(String.valueOf(cell.getBooleanCellValue()).trim());
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    //空白
                                    //cellValue ="";
                                    model.add("");
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    //错误
                                    model.add("错误");
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    //公式
                                    model.add("错误");
                                    break;
                                default:
                                    model.add("错误");
                            }
                        }else{
                            model.add("");
                        }
                    }
                    if (model.size() > 0) {
                        list.add(model);
                    }
                }
            }

        } catch (Exception e) {
            throw new BaseException(BaseErrorCode.READ_ERROR.getCode(),
                BaseErrorCode.READ_ERROR.getMessage(), "读取异常！");
        }
        return list;
    }

}
