package com.hu.base.util.excel;

import com.hu.base.util.DateFormatEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @title excel导入导出
 *
 * @author 石旺盛
 * @email wangsheng_shi@kingdee.com
 * @since 2017年7月7日
 * @version 1.0.0
 */
public class POIExcelUtil {
    private static Logger log = LoggerFactory.getLogger(POIExcelUtil.class);
    // 日期格式
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // 默认类长度
    public static final short DEFAULT_COLUMN_SIZE = 40;
    // 最大列长度
    public static final int MAX_COLUMN_SIZE = 50;
    // 最小列长度
    public static final int MIN_COLUMN_SIZE = 10;

    /**
     * @MethodName : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，可自定义工作表大小）
     * @param list 数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系 如果需要的是引用对象的属性，则英文属性使用类似于EL表达式的格式
     *            如：list中存放的都是student，student中又有college属性，而我们需要学院名称，则可以这样写
     *            fieldMap.put("college.collegeName","学院名称")
     * @param sheetName 工作表的名称
     * @param sheetSize 每个工作表中记录的最大个数
     * @param out 导出流
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
            int sheetSize, OutputStream out) {

        if (sheetSize > 65535 || sheetSize < 1) {
            sheetSize = 65535;
        }

        // 创建工作簿并发送到OutputStream指定的地方
        HSSFWorkbook wwb;
        try {
            wwb = new HSSFWorkbook();

            if (list.size() == 0 || list == null) {
                Sheet sheet = wwb.createSheet(sheetName);
                fillEmptySheet(wwb, sheet, fieldMap);

            } else {

                // 因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
                // 所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
                // 1.计算一共有多少个工作表
                double sheetNum = Math.ceil(list.size() / new Integer(sheetSize).doubleValue());

                // 2.创建相应的工作表，并向其中填充数据
                for (int i = 0; i < sheetNum; i++) {
                    // 如果只有一个工作表的情况
                    if (1 == sheetNum) {
                        Sheet sheet = wwb.createSheet(sheetName);
                        fillSheet(wwb, sheet, list, fieldMap, 0, list.size() - 1);

                        // 有多个工作表的情况
                    } else {
                        Sheet sheet = wwb.createSheet(sheetName + (i + 1));

                        // 获取开始索引和结束索引
                        int firstIndex = i * sheetSize;
                        int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1 ? list.size() - 1
                                : (i + 1) * sheetSize - 1;
                        // 填充工作表
                        fillSheet(wwb, sheet, list, fieldMap, firstIndex, lastIndex);
                    }
                }
            }

            wwb.write(out);

        } catch (Exception e) {
            log.error("导出失败", e);
        }

    }

    /**
     * @MethodName : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，工作表大小为2003支持的最大值）
     * @param list 数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系
     * @param out 导出流
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
            OutputStream out) {

        listToExcel(list, fieldMap, sheetName, 65535, out);

    }

    /**
     * @MethodName : listToExcel
     * @Description : 导出Excel（导出到浏览器，可以自定义工作表的大小）
     * @param list 数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系
     * @param sheetSize 每个工作表中记录的最大个数
     * @param response 使用response可以导出到浏览器
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
            int sheetSize, HttpServletResponse response, String fileName) {

        if (StringUtils.isEmpty(fileName)) {
            // 设置默认文件名为当前时间：年月日时分秒
            fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
        }

        // 设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-Excel"); // 改成输出Excel文件
        try {
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
        } catch (UnsupportedEncodingException e1) {
            fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
        }
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

        // 创建工作簿并发送到浏览器
        try {

            OutputStream out = response.getOutputStream();
            listToExcel(list, fieldMap, sheetName, sheetSize, out);

        } catch (Exception e) {
            log.error("导出失败", e);
        }
    }

    /**
     * @MethodName : outputExcel
     * @Description : 将Workbook通过response输出
     * @param response 使用response可以导出到浏览器
     */
    public static <T> void outputExcel(String sheetName, HttpServletResponse response, String fileName,
                                       HSSFWorkbook wwb) {

        if (StringUtils.isEmpty(fileName)) {
            // 设置默认文件名为当前时间：年月日时分秒
            fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
        }

        // 设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-Excel"); // 改成输出Excel文件
        try {
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
        } catch (UnsupportedEncodingException e1) {
            fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
        }
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

        // 创建工作簿并发送到浏览器
        try {
            OutputStream out = response.getOutputStream();
            wwb.write(out);
        } catch (Exception e) {
            log.error("导出失败", e);
        }
    }

    /**
     * @MethodName : listToExcel
     * @Description : 导出Excel（导出到浏览器，工作表的大小是2003支持的最大值）
     * @param list 数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系
     * @param response 使用response可以导出到浏览器
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
            HttpServletResponse response) {
        listToExcel(list, fieldMap, sheetName, 65535, response, null);
    }

    /**
     * @MethodName : listToExcel
     * @Description : 导出Excel（导出到浏览器，工作表的大小是2003支持的最大值）
     * @param list 数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系
     * @param response 使用response可以导出到浏览器
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
            HttpServletResponse response, String fileName) {

        listToExcel(list, fieldMap, sheetName, 65535, response, fileName);
    }

    /**
     * @MethodName : excelToList
     * @Description : 将Excel转化为List
     * @param in ：承载着Excel的输入流
     * @param sheetIndex ：要导入的工作表序号
     * @param entityClass ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
     * @param fieldMap ：Excel中的中文列头和类的英文属性的对应关系Map
     * @return ：List
     */
    public static <T> List<T> excelToList(InputStream in, int sheetIndex, int beginRowNum, Class<T> entityClass,
            Map<String, String> fieldMap) {

        // 定义要返回的list
        List<T> resultList = new ArrayList<T>();

        try {
            // 根据Excel数据源创建WorkBook
            Workbook wb = WorkbookFactory.create(in);
            Sheet sheet = wb.getSheetAt(sheetIndex);

            // 获取工作表的有效行数（包括标题行）
            Set<Integer> vaildRowSet = new HashSet<Integer>();
            for (Row row : sheet) {
                if (row.getRowNum() < beginRowNum) {
                    continue;
                }
                boolean nullFlag = true;
                for (Cell cell : row) {
                    if (cell != null && !"".equals(getStringValue(cell))) {
                        nullFlag = false;
                        break;
                    }
                }
                // 空行不处理，直接跳过
                if (nullFlag) {
                    continue;
                } else {
                    vaildRowSet.add(row.getRowNum());
                }
            }
            // 如果Excel中没有数据则提示错误
            if (vaildRowSet.isEmpty()) {
                log.error("文件中没有任何数据");
                return null;
            }

            // 获取表格中的标题
            Row titleRow = sheet.getRow(beginRowNum);
            Map<String, Integer> colFieldMap = new HashMap<String, Integer>();
            for (Cell fieldCell : titleRow) {
                colFieldMap.put(getStringValue(fieldCell), fieldCell.getColumnIndex());
            }

            // 判断Excel中的中文标题是否都存在对应的字段，保证模板中的字段全部对应，多余字段不处理
            Set<String> keyNames = fieldMap.keySet();
            Map<String, Map<String, Object>> vaildFieldMap = new HashMap<String, Map<String, Object>>();
            boolean flag = false;
            for (String title : keyNames) {
                if (colFieldMap.containsKey(title)) {
                    // 将列名和列号放入Map中,这样通过列名就可以拿到列号
                    Map<String, Object> fieldInfo = new HashMap<String, Object>();
                    fieldInfo.put("ENFIELDNAME", fieldMap.get(title));
                    fieldInfo.put("COLUMNINDEX", colFieldMap.get(title));
                    vaildFieldMap.put(title, fieldInfo);
                } else {
                    flag = true;
                    break;
                }
            }

            // 修改了模板标题字段，不处理
            if (flag) {
                log.error("标题字段对应不全");
                return null;
            }

            // 将sheet转换为list
            int rowStart = beginRowNum;
            int rowEnd = sheet.getLastRowNum();

            for (int rowNum = rowStart + 1; rowNum <= rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    // 空行直接记录
                    resultList.add(null);
                    continue;
                }
                // 新建要转换的对象
                T entity = entityClass.newInstance();
                if (vaildRowSet.contains(rowNum)) {
                    // 给对象中的字段赋值
                    for (Entry<String, Map<String, Object>> entry : vaildFieldMap.entrySet()) {
                        Map<String, Object> fieldInfo = entry.getValue();
                        // 获取英文字段名
                        String enNormalName = (String) fieldInfo.get("ENFIELDNAME");
                        // 获取列号
                        int col = (Integer) fieldInfo.get("COLUMNINDEX");

                        // 获取当前单元格中的内容
                        Cell cell = row.getCell(col);
                        if (cell != null) {
                            setFieldValueByName(enNormalName, getStringValue(cell), entity);
                        }
                    }
                    resultList.add(entity);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

    /**
     * @MethodName : excelToList
     * @Description : 将Excel转化为List
     * @param in ：承载着Excel的输入流
     * @param sheetIndex ：要导入的工作表序号
     * @param entityClass ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
     * @param fieldMap ：Excel中的列和类的英文属性的对应关系Map
     * @return ：List
     */
    public static <T> List<T> excelToListByColumn(InputStream in, int sheetIndex, int beginRowNum, Class<T> entityClass,
            Map<String, String> fieldMap) {

        // 定义要返回的list
        List<T> resultList = new ArrayList<T>();

        try {
            // 根据Excel数据源创建WorkBook
            Workbook wb = WorkbookFactory.create(in);
            Sheet sheet = wb.getSheetAt(sheetIndex);

            // 获取工作表的有效行数（包括标题行）
            Set<Integer> vaildRowSet = new HashSet<Integer>();
            for (Row row : sheet) {
                if (row.getRowNum() < beginRowNum) {
                    continue;
                }
                boolean nullFlag = true;
                for (Cell cell : row) {
                    if (cell != null && !"".equals(getStringValue(cell))) {
                        nullFlag = false;
                        break;
                    }
                }
                // 空行不处理，直接跳过
                if (nullFlag) {
                    continue;
                } else {
                    vaildRowSet.add(row.getRowNum());
                }
            }
            // 如果Excel中没有数据则提示错误
            if (vaildRowSet.isEmpty()) {
                log.error("文件中没有任何数据");
                return null;
            }

            // 将sheet转换为list
            int rowStart = beginRowNum;
            int rowEnd = sheet.getLastRowNum();

            for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    // 空行直接记录
                    resultList.add(null);
                    continue;
                }
                // 新建要转换的对象
                T entity = entityClass.newInstance();
                if (vaildRowSet.contains(rowNum)) {
                    // 给对象中的字段赋值
                    for (Entry<String, String> entry : fieldMap.entrySet()) {
                        // 获取列名称
                        String columnName = entry.getValue();
                        int col = getCol(columnName);
                        // 获取英文字段名
                        String fieldName = entry.getKey();

                        // 获取当前单元格中的内容
                        Cell cell = row.getCell(col);
                        if (cell != null) {
                            setFieldValueByName(fieldName, getStringValue(cell), entity);
                        }
                    }
                    resultList.add(entity);
                }
            }
        } catch (Exception e) {
            log.error("解析Excel失败", e);
        }
        return resultList;
    }

    /**
     * 通过列名获取列数
     * 
     * @param columnName
     * @return
     */
    private static int getCol(String columnName) {
        int column = 0;
        for (char a : columnName.toCharArray()) {
            column = column * 26 + (a - 'A' + 1);
        }
        return column - 1;
    }

    /**
     * 获取单元格作为字符串的值
     * 
     * @param cell
     * @return
     */
    private static String getStringValue(Cell cell) {
        if (cell.getCellType() != CellType.STRING) {
            if (cell.getCellType() == CellType.NUMERIC) {
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return com.hu.base.util.DateUtil.toString(cell.getDateCellValue(), DateFormatEnum.YEAR_MONTH_DAY_HH_MM_SS);
                }
            }
            cell.setCellType(CellType.STRING);
        }
        return cell.getStringCellValue().trim();
    }

    /*
     * <-------------------------辅助的私有方法----------------------------------------
     * ------->
     */
    /**
     * @MethodName : getFieldValueByName
     * @Description : 根据字段名获取字段值
     * @param fieldName 字段名
     * @param o 对象
     * @return 字段值
     */
    private static Object getFieldValueByName(String fieldName, Object o) throws Exception {

        Object value = null;
        Field field = getFieldByName(fieldName, o.getClass());

        if (field != null) {
            field.setAccessible(true);
            value = field.get(o);
        } else {
            log.error(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
            return null;
        }
        return value;
    }

    /**
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     * @param fieldName 字段名
     * @param clazz 包含该字段的类
     * @return 字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        // 拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        // 如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        // 否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }

        // 如果本类和父类都没有，则返回空
        return null;
    }

    /**
     * @MethodName : getFieldValueByNameSequence
     * @Description : 根据带路径或不带路径的属性名获取属性值
     *              即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     * 
     * @param fieldNameSequence 带路径的属性名或简单属性名
     * @param o 对象
     * @return 属性值
     * @throws Exception
     */
    private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {

        Object value = null;

        // 将fieldNameSequence进行拆分
        String[] attributes = fieldNameSequence.split("\\.");
        if (attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            // 根据属性名获取属性对象
            Object fieldObj = getFieldValueByName(attributes[0], o);
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;

    }

    /**
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     * @param fieldName 字段名
     * @param fieldValue 字段值
     * @param o 对象
     */
    private static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {

        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            // 获取字段类型
            Class<?> fieldType = field.getType();

            // 根据字段类型给字段赋值
            if (String.class == fieldType) {
                field.set(o, String.valueOf(fieldValue));
            } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                field.set(o, Integer.parseInt(fieldValue.toString()));
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                field.set(o, Long.valueOf(fieldValue.toString()));
            } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                field.set(o, Float.valueOf(fieldValue.toString()));
            } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                field.set(o, Short.valueOf(fieldValue.toString()));
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                Double value = null;
                try {
                    value = Double.valueOf(fieldValue.toString().replaceAll(",", ""));
                } catch (Exception e) {
                    log.debug("'" + fieldValue.toString() + "'无法转为double类型，设置为空");
                }
                field.set(o, value);
            } else if (BigDecimal.class == fieldType) {
                BigDecimal value = null;
                try {
                    value = new BigDecimal(fieldValue.toString().replaceAll(",", ""));
                } catch (Exception e) {
                    log.debug("'" + fieldValue.toString() + "'无法转为BigDecimal类型，设置为空");
                }
                field.set(o, value);
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(o, Character.valueOf(fieldValue.toString().charAt(0)));
                }
            } else if (Date.class == fieldType) {
//                field.set(o, DateUtils.toDate(fieldValue.toString()));
            } else {
                field.set(o, fieldValue);
            }
        } else {
            log.error(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
            return;
        }
    }

    /**
     * @MethodName : fillSheet
     * @Description : 向工作表中填充数据
     * @param sheet 工作表
     * @param list 数据源
     * @param fieldMap 中英文字段对应关系的Map
     * @param firstIndex 开始索引
     * @param lastIndex 结束索引
     */
    public static <T> void fillSheet(Workbook wb, Sheet sheet, List<T> list, Map<String, String> fieldMap,
            int firstIndex, int lastIndex) throws Exception {

        // 默认样式
//        CellStyle style = wb.createCellStyle();
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//
//        // 金额样式
//        CellStyle moneyStyle = wb.createCellStyle();
//        moneyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        moneyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        moneyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        moneyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        moneyStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//        DataFormat format = wb.createDataFormat();
//        moneyStyle.setDataFormat(format.getFormat("#,#0.0000"));
//
//        // 百分比样式
//        CellStyle percentageStyle = wb.createCellStyle();
//        percentageStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        percentageStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        percentageStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        percentageStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        percentageStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//        percentageStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));

        // 定义存放英文字段名和中文字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];

        // 填充数组
        int count = 0;
        for (Entry<String, String> entry : fieldMap.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
        // 最大列宽
        Map<Integer, Integer> maxSizes = new HashMap<Integer, Integer>();
        // 填充表头
        Row titleRow = sheet.createRow((short) 0);
        for (int i = 0; i < cnFields.length; i++) {
            titleRow.createCell(i).setCellValue(cnFields[i]);
            maxSizes.put(i, cnFields[i].getBytes().length);
        }

        setTitleStyle(wb, sheet, 0);

        for (int index = 0; index <= lastIndex - firstIndex; index++) {
            Row row = sheet.createRow(index + 1);
            // 获取单个对象
            T item = list.get(firstIndex + index);
            for (int i = 0; i < enFields.length; i++) {
                Object objValue = getFieldValueByNameSequence(enFields[i], item);
                Cell cell = row.createCell(i);
                String fieldValue = null;
                if (objValue == null) {
                    fieldValue = "";
                    cell.setCellValue(fieldValue);
                } else {
                    if (objValue.getClass() == Date.class) {
                        fieldValue = dateFormat.format(objValue).toString();
                    } else {
                        fieldValue = objValue.toString();
                    }
                    if (objValue.getClass() == Double.class || objValue.getClass() == double.class) {
                        cell.setCellValue((Double) objValue);

                    } else if (objValue.getClass() == BigDecimal.class) {
                        cell.setCellValue(((BigDecimal) objValue).doubleValue());

                    } else {
                        cell.setCellValue(fieldValue);
                    }

                }
//
//                // 设置样式
//                if (row.getCell(i).getCellType() == Cell.CELL_TYPE_NUMERIC) {
//                    if (StringUtils.contains(cnFields[i], "%")) {
//                        // 列中带%符号
//                        cell.setCellStyle(percentageStyle);
//                    } else {
//                        // 默认转为金额模式
//                        cell.setCellStyle(moneyStyle);
//                    }
//
//                } else {
//                    cell.setCellStyle(style);
//                }

                int size = fieldValue.getBytes().length;
                // 获取每列最大宽度
                Integer maxSize = maxSizes.get(i);
                if (maxSize == null) {
                    maxSizes.put(i, size);
                } else {
                    maxSize = size > maxSize ? size : maxSize;
                    maxSizes.put(i, maxSize);
                }

            }
        }
        // 自适应宽度
        for (int i = 0; i < enFields.length; i++) {
            // 跟最大列宽比较，是否大于最大列宽
            int size = maxSizes.get(i) > MAX_COLUMN_SIZE ? MAX_COLUMN_SIZE : maxSizes.get(i);
            // 跟最小列宽比较，是否小于最小列宽
            size = size < MIN_COLUMN_SIZE ? MIN_COLUMN_SIZE : size;
            sheet.setColumnWidth(i, size * 256);
        }
    }

    /**
     * @MethodName : fillSheet
     * @Description : 向工作表中填充数据
     * @param sheet 工作表
     * @param fieldMap 中英文字段对应关系的Map
     */
    public static <T> void fillEmptySheet(Workbook wb, Sheet sheet, Map<String, String> fieldMap) throws Exception {

        CellStyle style = wb.createCellStyle();

//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//
//        style.setFillForegroundColor(HSSFColor.WHITE.index);

        // 定义存放英文字段名和中文字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];

        // 填充数组
        int count = 0;
        for (Entry<String, String> entry : fieldMap.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
        // 最大列宽
        Map<Integer, Integer> maxSizes = new HashMap<Integer, Integer>();
        // 填充表头
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < cnFields.length; i++) {
            titleRow.createCell(i).setCellValue(cnFields[i]);
            maxSizes.put(i, cnFields[i].getBytes().length);
        }

        setTitleStyle(wb, sheet, 0);
        // 自适应宽度
        for (int i = 0; i < enFields.length; i++) {
            // 跟最大列宽比较，是否大于最大列宽
            int size = maxSizes.get(i) > MAX_COLUMN_SIZE ? MAX_COLUMN_SIZE : maxSizes.get(i);
            // 跟最小列宽比较，是否小于最小列宽
            size = size < MIN_COLUMN_SIZE ? MIN_COLUMN_SIZE : size;
            sheet.setColumnWidth(i, size * 256);
        }
    }

    /**
     * 设置表头格式
     * 
     * @param wb
     * @param sheet
     * @param rowIndex
     */
    public static void setTitleStyle(Workbook wb, Sheet sheet, int rowIndex) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
//        // 字体粗体
//        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        font.setFontHeightInPoints((short) 11);
//        style.setFont(font);
//
//        // 设置样式
//        // style.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//
//        style.setFillForegroundColor(HSSFColor.WHITE.index);

        Row row = sheet.getRow(rowIndex);
        if (row != null)
            for (Cell cell : row) {
                cell.setCellStyle(style);
            }

    }

    /**
     * 设置表头格式
     * 
     * @param wb
     * @param sheet
     * @param rowIndex
     */
    public static void setBodyStyle(Workbook wb, Sheet sheet, int rowIndex) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
//        // 字体粗体
//        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        font.setFontHeightInPoints((short) 11);
//        style.setFont(font);
//
//        // 设置样式
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        Row row = sheet.getRow(rowIndex);
        if (row != null)
            for (Cell cell : row) {
                cell.setCellStyle(style);
            }

    }

}