package com.pgh.spring_data_jpa0414.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/2/13
 */
public class EncryptConfigUtil {
    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt
        textEncryptor.setPassword("123456");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("123456");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
