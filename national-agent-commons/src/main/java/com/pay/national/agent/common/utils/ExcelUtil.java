package com.pay.national.agent.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.StringUtil;

import com.pay.national.agent.common.annotation.ModelProp;
import com.pay.national.agent.common.annotation.ModelTitle;
import com.pay.national.agent.common.bean.ImportModel;

public class ExcelUtil {
    final static String notnullerror = "请填入第{0}行的{1},{2}不能为空";
    final static String errormsg = "第{0}行的{1}数据导入错误";
    private final static int colsizeN = 630;
    private final static int colsizeM = 1000;
    
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy", Locale.US);
    
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 导入Excel
     * 
     * @param clazz 需要解析成的javabean对象
     * @param xls 导入的excel
     * @param startRowNum 从第几行开始（0代表第一行）
     * @return 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static List importExcel(Class<?> clazz, InputStream xls,Integer startRowNum) throws Exception {
        try {
            // 取得Excel
            HSSFWorkbook wb = new HSSFWorkbook(xls);
            //获取excel的第一个sheet
            HSSFSheet sheet = wb.getSheetAt(0);
            //通过反射获取类的所有属性
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>(fields.length);
            //遍历出拥有ModelProp注解的属性
            for (Field field : fields) {
                if (field.isAnnotationPresent(ModelProp.class)) {
                    ModelProp modelProp = field.getAnnotation(ModelProp.class);
                    if (modelProp.colIndex() != -1) {
                        fieldList.add(field);
                    }
                }
            }
            // 行循环
            //新建一个list接收所有的excel数据，list长度为excel实际行数的两倍
            List<ImportModel> modelList = new ArrayList<ImportModel>(sheet.getPhysicalNumberOfRows() * 2);
            
            for (int i = startRowNum; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 数据模型
                ImportModel model = (ImportModel) clazz.newInstance();
                int nullCount = 0;
                Exception nullError = null;
                for (Field field : fieldList) {
                    ModelProp modelProp = field.getAnnotation(ModelProp.class);
                    HSSFCell cell = sheet.getRow(i).getCell(modelProp.colIndex());
                    try {
                        if (cell == null || cell.toString().length() == 0) {
                            nullCount++;
                            if (!modelProp.nullable()) {
                                nullError = new Exception(StringUtil.format(notnullerror,
                                        new String[] { "" + (1 + i), modelProp.name(), modelProp.name() }));

                            }
                        } else if (field.getType().equals(Date.class)) {
                            if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(), new Date(parseDate(parseString(cell))));
                            } else {
                                BeanUtils.setProperty(model, field.getName(),
                                        new Date(cell.getDateCellValue().getTime()));

                            }
                        } else if (field.getType().equals(Timestamp.class)) {
                            if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(),
                                        new Timestamp(parseDate(parseString(cell))));
                            } else {
                                BeanUtils.setProperty(model, field.getName(),
                                        new Timestamp(cell.getDateCellValue().getTime()));
                            }

                        } else if (field.getType().equals(java.sql.Date.class)) {
                            if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(),
                                        new java.sql.Date(parseDate(parseString(cell))));
                            } else {
                                BeanUtils.setProperty(model, field.getName(),
                                        new java.sql.Date(cell.getDateCellValue().getTime()));
                            }
                        } else if (field.getType().equals(java.lang.Integer.class)) {
                            if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(), (int) cell.getNumericCellValue());
                            } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(), Integer.parseInt(parseString(cell)));
                            }
                        } else if (field.getType().equals(java.math.BigDecimal.class)) {
                            if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(),
                                        new BigDecimal(cell.getNumericCellValue()));
                            } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(), new BigDecimal(parseString(cell)));
                            }
                        } else {
                            if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(),
                                        new BigDecimal(cell.getNumericCellValue()));
                            } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                                BeanUtils.setProperty(model, field.getName(), parseString(cell));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception(StringUtil.format(errormsg, new String[] { "" + (1 + i), modelProp.name() })
                                + "," + e.getMessage());
                    }
                }
                if (nullCount == fieldList.size()) {
                    break;
                }
                if (nullError != null) {
                    throw nullError;
                }
                modelList.add(model);
            }
            return modelList;

        } finally {
            xls.close();
        }
    }

    public static InputStream exportExcel(List<ImportModel> list,Class<?> clazz){
        try {
        	boolean title = clazz.isAnnotationPresent(ModelTitle.class);
        	HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            //设置标题样式
            if (!title) {
            	LogUtil.info("没有ModelTitle注解不进行标题设置");
            }else{
            	/**
                 * 设置标题样式
                 */
                HSSFCellStyle titleStyle = wb.createCellStyle();
                titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                HSSFFont font = wb.createFont();
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font.setFontHeight((short) 400);
                titleStyle.setFont(font);
                HSSFCell titleCell = sheet.createRow(0).createCell(0); // 创建第一行，并在该行创建单元格，设置内容，做为标题行
                /**
                 * 获取标题
                 */
                ModelTitle modelTitle = clazz.getAnnotation(ModelTitle.class);
                titleCell.setCellValue(new HSSFRichTextString(modelTitle.name()));
                titleCell.setCellStyle(titleStyle);
            }
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>();
            HSSFRow headRow;
            if(title){//如果有标题
            	headRow= sheet.createRow(1);
            }else{//如果没有标题
            	headRow= sheet.createRow(0);
            }
            int colSzie = 0;
            /**
             * 设置表头样式
             */
            HSSFCellStyle headStyle = wb.createCellStyle();
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont headFont = wb.createFont();
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            headFont.setFontHeight((short) 240);
            headStyle.setFont(headFont);
            for (Field field : fields) {
                if (field.isAnnotationPresent(ModelProp.class)) {
                    ModelProp modelProp = field.getAnnotation(ModelProp.class);
                    if (modelProp.colIndex() == -1)
                        continue;
                    fieldList.add(field);
                    HSSFCell cell = headRow.createCell(modelProp.colIndex());
                    cell.setCellValue(new HSSFRichTextString(modelProp.name()));
                    cell.setCellStyle(headStyle);
                    colSzie++;
                    sheet.autoSizeColumn((short) modelProp.colIndex());
                    sheet.setColumnWidth(modelProp.colIndex(), modelProp.name().length() * colsizeN + colsizeM);

                }
            }
            HSSFCellStyle cellStyle = wb.createCellStyle();
            HSSFDataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));
            int startRow = 1;//默认从第二行开始
            if(title){//若有标题从第三行开始
            	startRow=2;
            }
            //绑定数据
            for (int i = startRow; i < list.size()+startRow; i++) {
            	HSSFRow row = sheet.createRow(i);
            	ImportModel importModel = list.get(i-startRow);
                for (Field field : fieldList) {
                	ModelProp modelProp = field.getAnnotation(ModelProp.class);
                    HSSFCell cell = row.createCell(modelProp.colIndex());
                    cell.setCellStyle(cellStyle);
                	try {
                		if (field.getType().equals(String.class)) {
                            cell.setCellValue(BeanUtils.getProperty(importModel, field.getName()));
                        }else if (field.getType().equals(Date.class)) {
                        	String dateStr = BeanUtils.getProperty(importModel, field.getName());
                        	Date date = simpleDateFormat.parse(dateStr);
                        	cell.setCellValue(dateFormat.format(date));
                        } else {
                            cell.setCellValue(BeanUtils.getProperty(importModel, field.getName()));
                            
                        }
					} catch (Exception e) {
					}
                }
            }
            if(title){//若有标题，对第一行进行合并单元格
            	sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSzie - 1));
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                wb.write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] b = os.toByteArray();

            ByteArrayInputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    /**
     * 下载Excel模版
     * 
     * @param clazz
     * @param map
     * @param rowSize
     * @return
     */
    public static InputStream excelModelbyClass(Class<?> clazz, Map<Integer, String[]> map, Integer rowSize) {
        try {
        	HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
        	boolean title = clazz.isAnnotationPresent(ModelTitle.class);
            if (!title) {
            	LogUtil.info("导出excel模板，不需要title");
            }else{//如果有标题，设置标题样式
            	/**
                 * 设置标题样式
                 */
                HSSFCellStyle titleStyle = wb.createCellStyle();
                titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                HSSFFont font = wb.createFont();
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font.setFontHeight((short) 400);
                titleStyle.setFont(font);
                HSSFCell titleCell = sheet.createRow(0).createCell(0); // 创建第一行，并在该行创建单元格，设置内容，做为标题行
                /**
                 * 获取标题
                 */
                ModelTitle modelTitle = clazz.getAnnotation(ModelTitle.class);
                titleCell.setCellValue(new HSSFRichTextString(modelTitle.name()));
                titleCell.setCellStyle(titleStyle);
            	
            }
            //设置总行数的大小
            if (rowSize == null) {
                rowSize = 1000;
            }
            //
            Field[] fields = clazz.getDeclaredFields();
            HSSFRow headRow;
            if(title){
            	headRow = sheet.createRow(1);
            }else{
            	headRow = sheet.createRow(0);
            }
            int colSzie = 0;
            /**
             * 设置表头样式
             */
            HSSFCellStyle headStyle = wb.createCellStyle();
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont headFont = wb.createFont();
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            headFont.setFontHeight((short) 240);
            headStyle.setFont(headFont);
            List<Integer> cells = new ArrayList<Integer>();

            /**
             * 遍历含有ModelProp注解的属性，设置为表头
             */
            for (Field field : fields) {
                if (field.isAnnotationPresent(ModelProp.class)) {
                    ModelProp modelProp = field.getAnnotation(ModelProp.class);
                    if (modelProp.colIndex() == -1)
                        continue;
                    cells.add(modelProp.colIndex());
                    HSSFCell cell = headRow.createCell(modelProp.colIndex());
                    cell.setCellValue(new HSSFRichTextString(modelProp.name()));
                    cell.setCellStyle(headStyle);
                    colSzie++;
                    sheet.autoSizeColumn((short) modelProp.colIndex());
                    sheet.setColumnWidth(modelProp.colIndex(), modelProp.name().length() * colsizeN + colsizeM);

                    // 设置列为下拉框格式
                    if (map != null && map.get(new Integer(modelProp.colIndex())) != null) {
                        DVConstraint constraint = DVConstraint
                                .createExplicitListConstraint(map.get(modelProp.colIndex()));
                        CellRangeAddressList regions = new CellRangeAddressList(2, rowSize, modelProp.colIndex(),
                                modelProp.colIndex());
                        HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
                        sheet.addValidationData(dataValidation);
                    }
                }
            }
            HSSFCellStyle cellStyle = wb.createCellStyle();
            HSSFDataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));
            int startRow = 1;//默认从第二行开始
            if(title){//若有标题从第三行开始
            	startRow=2;
            }
            for (int i = startRow; i < rowSize; i++) {
                HSSFRow row = sheet.createRow(i);
                for (Integer integer : cells) {
                    HSSFCell cell = row.createCell(integer);
                    cell.setCellStyle(cellStyle);
                }
            }
            //如果有标题，那么合并第一行单元格
            if(title){
            	sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSzie - 1));
            }
            if (map != null) {
                for (Integer colIndex : map.keySet()) {
                    DVConstraint constraint = DVConstraint.createExplicitListConstraint(map.get(colIndex));
                    CellRangeAddressList regions = new CellRangeAddressList(2, 1000, colIndex, colIndex);
                    HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
                    sheet.addValidationData(dataValidation);
                }
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                wb.write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] b = os.toByteArray();

            ByteArrayInputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String parseString(HSSFCell cell) {
        return String.valueOf(cell).trim();
    }

    private static long parseDate(String dateString) throws ParseException {
        if (dateString.indexOf("/") == 4) {
            return new SimpleDateFormat("yyyy/MM/dd").parse(dateString).getTime();
        } else if (dateString.indexOf("-") == 4) {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime();
        } else if (dateString.indexOf("年") == 4) {
            return new SimpleDateFormat("yyyy年MM月dd").parse(dateString).getTime();
        } else if (dateString.length() == 8) {
            return new SimpleDateFormat("yyyyMMdd").parse(dateString).getTime();
        } else {
            return new Date().getTime();
        }
    }
}
