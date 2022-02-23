package com.pgh.spring_data_jpa0414.service;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/2/23
 */
public class FileTest {

//    public void exportExcle() {
//        //设置存储在内存的行数，多余的存储在硬盘
//        int cacheItems = 100;
//        Workbook wb = new SXSSFWorkbook(cacheItems);
//        Sheet sh = wb.createSheet();
//        ResultSet rs = queryData();//查询数据
//        int rownum = 0;
//        while(rs.next()){
//            Row row = sh.createRow(rownum);
//            Cell cell = row.createCell(0);
//            cell.setCellValue(rs.getString("c1"));
//            cell = row.createCell(1);
//            cell.setCellValue(rs.getString("c2"));
//    …
//            cell = row.createCell(19);
//            cell.setCellValue(rs.getString("c20"));
//            rownum ++;
//            //每当行数达到设置的值就刷新数据到硬盘,以清理内存
//            if(rownum % cacheItems == 0){
//                ((SXSSFSheet)sh).flushRows();
//            }
//        }
//        FileOutputStream out = new FileOutputStream("/excel_data.xlsx");
//        wb.write(out);
//        out.close();
//    }
}
