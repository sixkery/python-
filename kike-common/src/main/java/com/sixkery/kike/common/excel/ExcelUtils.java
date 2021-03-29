//package com.sixkery.excel;
//
//
//import com.sixkery.utils.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * Excel操作工具类
// *
// * @author lazy
// */
//public final class ExcelUtils {
//
//    private ExcelUtils() {
//    }
//
//    /**
//     * 直接下载生成的Excel
//     *
//     * @param name
//     * @param sheets
//     * @param response
//     */
//    public static <T> void download(String name, List<ExcelSheet<T>> sheets, HttpServletResponse response) {
//        String fileName = name + ".xlsx";
//        try (BufferedOutputStream bos = getBufferedOutputStream(fileName, response)) {
//            create(sheets, bos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 读取Excel，默认加载第一个Sheet
//     *
//     * @param filePath
//     * @return
//     */
//    public static List<Map<String, Object>> read(String filePath) {
//        try {
//            return read(filePath.substring(filePath.lastIndexOf(".")), new FileInputStream(filePath));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 读取Excel
//     *
//     * @param suffix
//     * @param stream
//     * @return
//     */
//    public static List<Map<String, Object>> read(String suffix, InputStream stream) {
//        return read(suffix, stream, 0);
//    }
//
//    /**
//     * 读取Excel
//     *
//     * @param sheetIndex
//     * @return
//     */
//    public static List<Map<String, Object>> read(String suffix, InputStream stream, Integer sheetIndex) {
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        Workbook wb = createWorkBook(suffix, stream);
//        if (wb != null) {
//            Sheet sheet = wb.getSheetAt(sheetIndex);
//            int maxRowNum = sheet.getPhysicalNumberOfRows();
//            Row firstRow = sheet.getRow(0);
//            int maxColNum = firstRow.getPhysicalNumberOfCells();
//            String[] columns = new String[maxColNum];
//            for (int i = 0; i < maxRowNum; i++) {
//                Map<String, Object> map = null;
//                if (i > 0) {
//                    map = new LinkedHashMap<>();
//                    firstRow = sheet.getRow(i);
//                }
//                if (firstRow != null) {
//                    String cellData;
//                    for (int j = 0; j < maxColNum; j++) {
//                        cellData = (String) getCellFormatValue(firstRow.getCell(j));
//                        if (i == 0) {
//                            columns[j] = cellData;
//                        } else {
//                            map.put(columns[j], cellData);
//                        }
//                    }
//                } else {
//                    break;
//                }
//                if (i > 0) {
//                    dataList.add(map);
//                }
//            }
//        }
//        return dataList;
//    }
//
//    /**
//     * 生成Excel
//     *
//     * @param sheets
//     * @param outputStream
//     * @param <T>
//     */
//    public static <T> void create(List<ExcelSheet<T>> sheets, OutputStream outputStream) {
//        int maxBuff = 100;
//        // 创建excel工作文本，100表示默认允许保存在内存中的行数
//        SXSSFWorkbook wb = new SXSSFWorkbook(maxBuff);
//        try {
//            for (int i = 0; i < sheets.size(); i++) {
//                ExcelSheet<T> sheet = sheets.get(i);
//                if (StringUtils.isEmpty(sheet.getName())) {
//                    sheet.setName("Sheet" + (i + 1));
//                }
//                createSheet(wb, sheet, maxBuff);
//            }
//            if (outputStream != null) {
//                wb.write(outputStream);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 生成Excel
//     *
//     * @param sheet
//     * @param outputStream
//     * @param <T>
//     */
//    public static <T> void create(ExcelSheet<T> sheet, OutputStream outputStream) {
//        int maxBuff = 100;
//        // 创建excel工作文本，100表示默认允许保存在内存中的行数
//        SXSSFWorkbook wb = new SXSSFWorkbook(maxBuff);
//        try {
//            if (StringUtils.isEmpty(sheet.getName())) {
//                sheet.setName("Sheet1");
//            }
//            createSheet(wb, sheet, maxBuff);
//            if (outputStream != null) {
//                wb.write(outputStream);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 设置下载
//     *
//     * @param fileName
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    private static BufferedOutputStream getBufferedOutputStream(String fileName, HttpServletResponse response)
//            throws IOException {
//        response.setContentType("application/x-msdownload");
//        response.setHeader("Content-Disposition", "attachment;filename="
//                + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
//        return new BufferedOutputStream(response.getOutputStream());
//    }
//
//    /**
//     * @param wb
//     * @param sheet
//     * @param maxBuff
//     * @param <T>
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     * @throws IOException
//     */
//    private static <T> void createSheet(SXSSFWorkbook wb, ExcelSheet<T> sheet, int maxBuff)
//            throws NoSuchFieldException, IllegalAccessException, IOException {
//        Sheet sh = wb.createSheet();
//
//        //创建表头
//        CellStyle style = wb.createCellStyle();
//        Font font1 = wb.createFont();
//        font1.setFontName("微软雅黑");
//        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        font1.setFontHeightInPoints((short) 12);                    // 设置字体大小
//        style.setFont(font1);                                       // 选择需要用到的字体格式
//        style.setFillForegroundColor(HSSFColor.YELLOW.index);       // 设置背景色
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);               // 水平居左
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);           // 下边框
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);            // 右边框
//
//        //表头
//        Row headerRow = sh.createRow(0);
//        headerRow.setHeight((short) (25 * 20));                      // 设置表头行高
//        String[] headers = sheet.getHeaders();
//
//        int headerSize = headers.length;
//        for (int cellNum = 0; cellNum < headerSize; cellNum++) {
//            Cell cell = headerRow.createCell(cellNum);
//            cell.setCellStyle(style);
//            // 设置表格自动列宽
//            sh.autoSizeColumn(cellNum, true);
//            cell.setCellValue(headers[cellNum]);
//        }
//
//        int rowNum = 0;
//        for (T data : sheet.getData()) {
//            Row row = sh.createRow(rowNum + 1);
//            Field[] fields = getExportFields(data.getClass(), sheet.getExportFields());
//            for (int cellNum = 0; cellNum < headerSize; cellNum++) {
//                Cell cell = row.createCell(cellNum);
//                Field field = fields[cellNum];
//                // 单元格样式
//                CellStyle style2 = wb.createCellStyle();
//                Font font2 = wb.createFont();
//                font2.setFontName("微软雅黑");
//                font2.setFontHeightInPoints((short) 11);                    // 设置字体大小
//                style2.setFont(font2);                                      // 选择需要用到的字体格式
//                style2.setFillForegroundColor(HSSFColor.WHITE.index);       // 设置背景色
//                style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//                style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);              // 水平向左居中
//                style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
//                style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);          // 下边框
//                style2.setBorderRight(HSSFCellStyle.BORDER_THIN);           // 右边框
//                style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);            // 左边框
//                style2.setBorderTop(HSSFCellStyle.BORDER_THIN);             // 上边框
//                int valLength = setData(field, data, field.getName(), cell);
//                if (valLength > 30) {
//                    style2.setWrapText(true);
//                }
//                cell.setCellStyle(style2);
//                sh.autoSizeColumn(cellNum, true);
//            }
//            rowNum = sh.getLastRowNum();
//            // 大数据量时将之前的数据保存到硬盘
//            if (rowNum % maxBuff == 0) {
//                ((SXSSFSheet) sh).flushRows(maxBuff); // 超过100行后将之前的数据刷新到硬盘
//            }
//        }
//    }
//
//    /**
//     * 获取单条数据的属性
//     *
//     * @param object
//     * @param property
//     * @param <T>
//     * @return
//     * @throws NoSuchFieldException
//     */
//    private static <T> Field getDataField(T object, String property) throws NoSuchFieldException {
//        Field dataField;
//        if (property.contains(".")) {
//            String p = property.substring(0, property.indexOf("."));
//            dataField = object.getClass().getDeclaredField(p);
//            return dataField;
//        } else {
//            dataField = object.getClass().getDeclaredField(property);
//        }
//        return dataField;
//    }
//
//    private static Field[] getExportFields(Class<?> targetClass, String[] exportFieldNames) {
//        Field[] fields;
//        if (exportFieldNames == null || exportFieldNames.length < 1) {
//            fields = targetClass.getDeclaredFields();
//        } else {
//            fields = new Field[exportFieldNames.length];
//            for (int i = 0; i < exportFieldNames.length; i++) {
//                try {
//                    fields[i] = targetClass.getDeclaredField(exportFieldNames[i]);
//                } catch (Exception e) {
//                    try {
//                        fields[i] = targetClass.getSuperclass().getDeclaredField(exportFieldNames[i]);
//                    } catch (Exception e1) {
//                        throw new IllegalArgumentException("无法获取导出字段", e);
//                    }
//                }
//            }
//        }
//        return fields;
//    }
//
//    /**
//     * 根据属性设置对应的属性值
//     *
//     * @param dataField 属性
//     * @param object    数据对象
//     * @param property  表头的属性映射
//     * @param cell      单元格
//     * @param <T>
//     * @return
//     * @throws IllegalAccessException
//     * @throws NoSuchFieldException
//     */
//    private static <T> int setData(Field dataField, T object, String property, Cell cell)
//            throws IllegalAccessException, NoSuchFieldException {
//        dataField.setAccessible(true);              // 允许访问private属性
//        Object val = dataField.get(object);         // 获取属性值
//        Sheet sh = cell.getSheet();                 // 获取excel工作区
//        CellStyle style = cell.getCellStyle();      // 获取单元格样式
//        int valLength = 0;
//        int cellNum = cell.getColumnIndex();
//        if (val != null) {
//            String fieldType = dataField.getType().toString();
//            if (fieldType.endsWith("String")) {
//                cell.setCellValue((String) val);
//                valLength = ((String) val).length();
//            } else if (fieldType.endsWith("Integer") || fieldType.endsWith("int")) {
//                cell.setCellValue(val.toString());
//                valLength = val.toString().length();
//            } else if (fieldType.endsWith("Long") || fieldType.endsWith("long")) {
//                cell.setCellValue(val.toString());
//                valLength = val.toString().length();
//            } else if (fieldType.endsWith("Double") || fieldType.endsWith("double")) {
//                cell.setCellValue(val.toString());
//            } else if (fieldType.endsWith("Date")) {
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                cell.setCellValue(format.format((Date) val));
//                valLength = cell.getStringCellValue().length();
//            } else if (fieldType.endsWith("List")) {
//                List list1 = (List) val;
//                int size = list1.size();
//                int maxLength = 0;
//                for (int i = 0; i < size; i++) {
//                    // 加1是因为要去掉点号
//                    int start = property.indexOf(dataField.getName()) + dataField.getName().length() + 1;
//                    String tempProperty = property.substring(start);
//                    Field field = getDataField(list1.get(i), tempProperty);
//                    Cell tempCell = cell;
//                    if (i > 0) {
//                        int rowNum = cell.getRowIndex() + i;
//                        Row row = sh.getRow(rowNum);
//                        if (row == null) {
//                            // 另起一行
//                            row = sh.createRow(rowNum);
//                            // 合并之前的空白单元格（在这里需要在header中按照顺序把list类型的字段放到最后，方便显示和合并单元格）
//                            for (int j = 0; j < cell.getColumnIndex(); j++) {
//                                sh.addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex() + size - 1, j, j));
//                                Cell c = row.createCell(j);
//                                c.setCellStyle(style);
//                            }
//                        }
//                        tempCell = row.createCell(cellNum);
//                        tempCell.setCellStyle(style);
//                    }
//                    //递归传参到单元格并获取偏移量（这里获取到的偏移量都是第二层后list的偏移量）
//                    int result;
//                    if ((result = setData(field, list1.get(i), tempProperty, tempCell)) > maxLength) {
//                        maxLength = result;
//                    }
//                }
//                valLength = maxLength;
//            } else {
//                if (property.contains(".")) {
//                    String p = property.substring(property.indexOf(".") + 1);
//                    Field field = getDataField(val, p);
//                    setData(field, val, p, cell);
//                } else {
//                    cell.setCellValue(val.toString());
//                }
//                // ...
//            }
//            return valLength;
//        }
//        return 0;
//    }
//
//    /**
//     * 创建一个WorkBook
//     *
//     * @param suffix
//     * @param stream
//     * @return
//     */
//    private static Workbook createWorkBook(String suffix, InputStream stream) {
//        try {
//            if (".xls".equals(suffix)) {
//                return new HSSFWorkbook(stream);
//            } else if (".xlsx".equals(suffix)) {
//                return new XSSFWorkbook(stream);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 将字段转为相应的格式
//     *
//     * @param cell
//     * @return
//     */
//    private static Object getCellFormatValue(Cell cell) {
//        Object cellValue;
//        if (cell != null) {
//            //判断cell类型
//            switch (cell.getCellType()) {
//                case Cell.CELL_TYPE_NUMERIC: {
//                    String val = String.valueOf(cell.getNumericCellValue());
//                    if ("0".equals(val.substring(val.indexOf(".") + 1))) {
//                        cellValue = val.substring(0, val.indexOf("."));
//                    } else {
//                        if (val.endsWith("E10")) {
//                            cellValue = new DecimalFormat("#").format(cell.getNumericCellValue());
//                        } else {
//                            cellValue = val;
//                        }
//                    }
//                    break;
//                }
//                case Cell.CELL_TYPE_FORMULA: {
//                    if (DateUtil.isCellDateFormatted(cell)) {
//                        cellValue = cell.getDateCellValue(); // 转换为日期格式YYYY-mm-dd
//                    } else {
//                        cellValue = String.valueOf(cell.getNumericCellValue()); //数字
//                    }
//                    break;
//                }
//                case Cell.CELL_TYPE_STRING: {
//                    cellValue = cell.getRichStringCellValue().getString();
//                    break;
//                }
//                default:
//                    cellValue = "";
//            }
//        } else {
//            cellValue = "";
//        }
//        return cellValue;
//    }
//
//}
//
//
