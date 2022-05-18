package com.pgh.spring_data_jpa0414.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/1/11
 */
@Data
@Entity
@Table(name = "tb_user")
@Cacheable(false)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="p_desc")
    private String desc;

    @Column(name="update_date", updatable = false)
    private Date updateDt;

    private Instant createDt;

    private LocalDateTime createDt2;

    @Version
    private Long version;

    private transient BigDecimal price;
}
