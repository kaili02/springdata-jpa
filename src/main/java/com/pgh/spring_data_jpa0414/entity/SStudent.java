package com.pgh.spring_data_jpa0414.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author pgh
 * @data 2021/4/13 18:22
 */
@Data
@Entity
@Table (name = "s_student")
public class SStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="s_id")
    private int sid;
    @Column(name="s_name")
    private String sname;
    @Column(name="s_sex")
    private String sex;
}
