package com.pgh.spring_data_jpa0414.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pgh
 * @data 2021/4/21 16:39
 *
 *自定义列表显示字段表实体类
 *
 */
@Data
@Entity
@Table(name = "common_show_field")
public class CommonShowFieldBean {
    /**
     * 主键自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 自定义列表显示的字段
     */
    @Column(name = "fields_list")
    private String fieldsList;
    /**
     * 标识
     */
        @Column(name = "type")
    private String type;
    /**
     * 创建的用户
     */
    @Column(name = "create_user")
    private String createUser;
    /**
     * 创建的时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改的用户
     */
    @Column(name = "update_user")
    private String updateUser;
    /**
     * 修改的时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 描述
     */
    @Column(name="describe")
    private String describe;

    @Transient

    private String [] fieldarray;


//    /**
//     * 获取数据map集合
//     *
//     * @return map
//     */
//    public Map<String, Object> getAssetMap() {
//        //结果集
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", this.id);
//        map.put("fieldsList", this.fieldsList);
//        map.put("type", this.type);
//        map.put("createUser", this.createUser);
//        map.put("createTime", this.createTime);
//        map.put("updateUser", this.updateUser);
//        map.put("updateTime", this.updateTime);
//        map.put("describe", this.describe);
//        //map.put("fieldarray",this.fieldarray);
//        return map;
//    }



}
