package com.pgh.spring_data_jpa0414.service;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;


/**
 * @ClassName:  ExcelExport2007
 * @author: 谈荣涛
 * @date:   2018-12-19 下午02:37:46
 * @Description:TODO(这里用一句话描述这个类的作用)
 *
 */
public class ExcelExport2007 {

    public static void main(String[] args) {
        getExcelExport();
    }
    /**
     * @Title: getExcelExport
     * @Description: TODO(excel方式导出)
     * @param:
     * @return: void
     * @throws
     */
    public static void getExcelExport(HttpServletResponse response) {

        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
        System.err.println("-----------------------------2007 100W导数开始时间：------------------------\n" + nowTimestamp);


        SXSSFWorkbook workBook = new SXSSFWorkbook();
        //创建HSSFWorkbook对象(excel的文档对象)   POI要操作excel 2007及以上的版本需要使用XSSF来代替上面代码的HSSF。
        //XSSFWorkbook workBook = new XSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        Sheet sheet = workBook.createSheet("Excel 2007导出");       //创建Excel工作表（页签）
        int[] width = {5000,5000,5000,5000,5000,5000,5000,5000};
        for(int i=0; i < width.length; i++){
            sheet.setColumnWidth(i, width [i]);                       //设置列宽
        }

        //excel列
        String[] head = {"列1", "列2", "列3", "列4", "列5", "列6", "列7", "列8"};
        Row title = sheet.createRow(0);                            //创建标题行
        title.createCell(0).setCellValue("Excel 2007导出测试");        //给标题行单元格赋值
        //合并单元格          构造参数依次为起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
        getTitleStyle(workBook, title);                   //创建并初始化标题样式
        InitExcelHead(workBook, sheet, head);             //初始化抬头和样式
        setExcelValue(workBook, sheet, head);		      //excel内容赋值
        try {
            excelExport(workBook, response);                            //导出处理
        } catch (IOException e) {
            e.printStackTrace();
        }


        Timestamp nowTimestamp1 = new Timestamp(System.currentTimeMillis());
        System.err.println("-----------------------------2007 100W导数结束时间：------------------------\n" + nowTimestamp1);


    }
    public static void getExcelExport() {

        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
        System.err.println("-----------------------------2007 100W导数开始时间：------------------------\n" + nowTimestamp);


        SXSSFWorkbook workBook = new SXSSFWorkbook();
        //创建HSSFWorkbook对象(excel的文档对象)   POI要操作excel 2007及以上的版本需要使用XSSF来代替上面代码的HSSF。
        //XSSFWorkbook workBook = new XSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        Sheet sheet = workBook.createSheet("Excel 2007导出");       //创建Excel工作表（页签）
        int[] width = {5000,5000,5000,5000,5000,5000,5000,5000};
        for(int i=0; i < width.length; i++){
            sheet.setColumnWidth(i, width [i]);                       //设置列宽
        }

        //excel列
        String[] head = {"列1", "列2", "列3", "列4", "列5", "列6", "列7", "列8"};
        Row title = sheet.createRow(0);                            //创建标题行
        title.createCell(0).setCellValue("Excel 2007导出测试");        //给标题行单元格赋值
        //合并单元格          构造参数依次为起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
        getTitleStyle(workBook, title);                   //创建并初始化标题样式
        InitExcelHead(workBook, sheet, head);             //初始化抬头和样式
        setExcelValue(workBook, sheet, head);		      //excel内容赋值
        try {
            excelExport(workBook, null);                            //导出处理
        } catch (IOException e) {
            e.printStackTrace();
        }


        Timestamp nowTimestamp1 = new Timestamp(System.currentTimeMillis());
        System.err.println("-----------------------------2007 100W导数结束时间：------------------------\n" + nowTimestamp1);


    }



    /**
     * 标题样式
     * @param workbook
     * 创建并初始化标题样式
     *
     */
    public static void getTitleStyle(SXSSFWorkbook workbook, Row title) {

        CellStyle style = workbook.createCellStyle();              // 创建样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);            // 字体居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        Font font = workbook.createFont();                         // 创建字体样式
        font.setFontName("宋体");                                   // 字体
        font.setFontHeightInPoints((short) 16);                    // 字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);              // 加粗
        style.setFont(font);                         //给样式指定字体
        title.getCell(0).setCellStyle(style);        //给标题设置样式

    }



    /**
     * @param workBook
     * @param sheet
     * @param head
     * @Title: CreateExcelHead
     * @Description: TODO(初始化Excel表头)
     * @param:
     * @return: void
     * @throws
     */
    private static Row InitExcelHead(SXSSFWorkbook workBook, Sheet sheet, String[] head) {

        Row row = sheet.createRow(1);
        CellStyle style = getHeaderStyle(workBook);             //获取表头样式
        for(int i=0; i<head.length; i++){
            row.createCell(i).setCellValue(head [i]);
            row.getCell(i).setCellStyle(style);                 //设置标题样式
        }

        return row;

    }



    /**
     * @Title: setExcelValue
     * @Description: TODO( excel正文内容的填充 )
     * @param: @param sheet  Excel页签对象名
     * @return: void
     * @throws
     */
    private static void setExcelValue(SXSSFWorkbook workBook, Sheet sheet, String[] head) {

        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<1000000; i++){
            //sheet.createRow(i+2) 2003excel参数里面的类型是int，所以一次只能导出65535条数据
            Row row = sheet.createRow(i+2);
            for(int j=0; j < head.length; j++){
                buffer.append("数据行"+(i+1));
                buffer.append("列"+(j+1));
                row.createCell(j).setCellValue(buffer.toString());
                buffer.delete(0, buffer.length());
            }
        }

    }


    /**
     * @Title: excelExport
     * @Description: TODO(excel导出类)
     * @param: @param sheet
     * @return: void
     * @throws
     */
    private static void excelExport(SXSSFWorkbook workBook, HttpServletResponse response) throws IOException {


        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=Excel多线程导出.xlsx");

        String filePath = getSavePath();  //获取文件保存路径
        if(filePath == null){
            return;    //终止程序
        }

        String srcFile = filePath + "\\Excel导出测试根目录\\Excel多线程导出.xlsx";
        FileOutputStream fileOut = null ;
        try {
            File file = new File(srcFile);
            if(file.exists()){  //当文件已存在时
                //删除原Excel      打开新导出的Excel时，最好刷新下当前文件夹，以免重复操作有时出现缓存。
                file.delete();
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            fileOut = new FileOutputStream(file);
//            workBook.write(fileOut);
            workBook.write(outputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * @Title: getSavePath
     * @Description: TODO(获取文件保存路径)
     * @param: @return
     * @return: String
     * @throws
     */
    private static String getSavePath() {
        return "C:\\Users\\Admin\\Documents\\";
        // 选择保存路径
//        String selectPath = null;
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//设置只能选择目录
//        int returnVal = chooser.showOpenDialog(null);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            selectPath =chooser.getSelectedFile().getPath() ;
//        }
//        return selectPath;
    }



    /**
     * 获取Excel表头样式(第二行)
     * @param workbook
     * @return
     */
    public static CellStyle getHeaderStyle(SXSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);  //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);    //左边框
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);      //居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        style.setTopBorderColor(HSSFColor.BLACK.index);     //上边框颜色
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        Font font = workbook.createFont();               // 创建字体样式
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);              // 字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);        // 加粗
        style.setFont(font);                                 //给样式指定字体

        return style;


    }


}
