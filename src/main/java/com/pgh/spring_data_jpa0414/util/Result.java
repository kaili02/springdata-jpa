package com.pgh.spring_data_jpa0414.util;

import lombok.Data;

@Data

/**
 * @author pgh
 * @data 2021/4/14 17:09
 */

public class Result<T> {




        /**
         *成功标志
         */
        private boolean success =true;
        /**
         *返回处理消息
         */
        private String message ="操作成功";
        /**
         * 返回消息的状态码
         */
        private int code=0;
        /**
         * 返回的数据
         */
        private T result;

        public static Result ok(Object o){
                Result<Object> result = new Result<>();
                result.setResult(o);
                return result;
        }


}
