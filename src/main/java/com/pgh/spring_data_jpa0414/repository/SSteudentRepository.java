package com.pgh.spring_data_jpa0414.repository;


import com.pgh.spring_data_jpa0414.entity.SStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author pgh
 * @data 2021/4/13 18:31
 */

public interface SSteudentRepository extends JpaRepository<SStudent,Integer>, JpaSpecificationExecutor<SStudent> {

    @Query(value = "select * from s_student where s_name like ?% and s_id=? ",nativeQuery = true)
    List findbyid(String sname,int sid);
}
