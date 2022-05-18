package com.pgh.spring_data_jpa0414.util;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/4/28
 */
public class Test1 {
    public static void main(String[] args) {
        Object o = new Object();
        String str2 = "1" + "2222";
        String str = "12222";
        System.out.println(str == str2);
        String str3 = new String("12222");
        System.out.println(str == str3);
    }
}
