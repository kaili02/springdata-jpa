package com.pgh.spring_data_jpa0414.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/4/7
 */
public class TestPoi {
    public static void main(String[] args) throws Exception {
        new TestPoi().bigDataExport();
    }

    public void bigDataExport() throws Exception {

        BigDecimal bigDecimal = new BigDecimal("1");
        List<MsgClient> list = new ArrayList<MsgClient>();
        Workbook workbook = null;
        Date start = new Date();
        ExportParams params = new ExportParams("大数据测试", "测试");
        for (int i = 0; i < 1000000; i++) {  //一百万数据量
            MsgClient client = new MsgClient();
            client.setBirthday(new Date());
            client.setClientName("小明" + i);
            client.setClientPhone("18797" + i);
            client.setCreateBy("JueYue");
            client.setId("1" + i);
            client.setRemark("测试" + i);
            MsgClientGroup group = new MsgClientGroup();
            group.setGroupName("测试" + i);
            client.setGroup(group);
            list.add(client);
            if(list.size() == 10000){
                workbook = ExcelExportUtil.exportExcel(params, MsgClient.class, list);
                list.clear();
            }
        }
//        ExcelExportUtil.closeExportBigExcel();
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportBigData.bigDataExport.xlsx");
        workbook.write(fos);
        fos.close();
    }

    @Setter
    @Getter
    class MsgClient {
        String id;
        @Excel(name = "生日", height = 20, width = 30, isImportField = "true_st")
        Date birthday;
        @Excel(name = "名字", height = 20, width = 30, isImportField = "true_st")
        String clientName;
        @Excel(name = "电话", height = 20, width = 30, isImportField = "true_st")
        String clientPhone;
        @Excel(name = "创建人", height = 20, width = 30, isImportField = "true_st")
        String createBy;
        String remark;
        MsgClientGroup group;
    }
    @Setter
    @Getter
    class MsgClientGroup {
        String groupName;
    }

    @ExcelTarget("teacherEntity")
    public static class TeacherEntity implements java.io.Serializable {
        private String id;
        /** name */
        @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1", isImportField = "true_major,true_absent")
        private String name;
    }

    @ExcelTarget("courseEntity")
    public static class CourseEntity implements java.io.Serializable {
        /** 主键 */
        private String        id;
        /** 课程名称 */
        @Excel(name = "课程名称", orderNum = "1", width = 25)
        private String        name;
        /** 老师主键 */
        @ExcelEntity(id = "absent")
        private TeacherEntity mathTeacher;

        @ExcelCollection(name = "学生", orderNum = "4")
        private List<StudentEntity> students;
    }

    public class StudentEntity implements java.io.Serializable {
        /**
         * id
         */
        private String        id;
        /**
         * 学生姓名
         */
        @Excel(name = "学生姓名", height = 20, width = 30, isImportField = "true_st")
        private String        name;
        /**
         * 学生性别
         */
        @Excel(name = "学生性别", replace = { "男_1", "女_2" }, suffix = "生", isImportField = "true_st")
        private int           sex;

        @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
        private Date          birthday;

        @Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
        private Date registrationDate;

    }
}
